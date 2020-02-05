package com.ixuea.courses.mymusic.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.ixuea.courses.mymusic.AppContent;
import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.api.Api;
import com.ixuea.courses.mymusic.domain.Session;
import com.ixuea.courses.mymusic.domain.User;
import com.ixuea.courses.mymusic.domain.response.DetailResponse;
import com.ixuea.courses.mymusic.listener.HttpObserver;
import com.ixuea.courses.mymusic.util.StringUtil;
import com.ixuea.courses.mymusic.util.ToastUtil;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseLoginActivity {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick(){
        String userName = et_username.getText().toString().trim();
        if(StringUtils.isBlank(userName)) {
            ToastUtil.errorLongToast(R.string.error_username);
            return;
        }
        String pwd = et_pwd.getText().toString().trim();
        if(StringUtils.isBlank(pwd)) {
            ToastUtil.errorLongToast(R.string.error_pwd);
            return;
        }

        boolean isP = StringUtil.isPhone(userName);
        boolean isE = StringUtil.isEmail(userName);
//        LogUtil.d("eee","isPhone:" + isP + ",isEmail:" + isE);
        if(isP && isE){
            ToastUtil.errorShortToast(R.string.login_username_formate);
            return;
        }

        //判断密码长度的格式
        if (!StringUtil.isPassword(pwd)) {
            ToastUtil.errorShortToast(R.string.error_password_format);
            return;
        }

//        ToastUtil.shortSuccessToast(R.string.login_success);
        //判断是手机号还是邮箱
        String phone = null;
        String email = null;

        if (StringUtil.isPhone(userName)) {
            //手机号
            phone = userName;
        } else {
            //邮箱
            email = userName;
        }

        User user = new User();

//这里虽然同时传递了手机号和邮箱
//但服务端登录的时候有先后顺序
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(pwd);
        //调用登录接口
       login(user);
    }

    //忘记密码点击事件
    @OnClick(R.id.btn_forget)
    public void onForgetClick(){

    }

}
