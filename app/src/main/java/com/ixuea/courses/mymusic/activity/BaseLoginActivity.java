package com.ixuea.courses.mymusic.activity;

import android.app.Application;

import com.ixuea.courses.mymusic.AppContent;
import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.api.Api;
import com.ixuea.courses.mymusic.domain.Session;
import com.ixuea.courses.mymusic.domain.User;
import com.ixuea.courses.mymusic.domain.response.DetailResponse;
import com.ixuea.courses.mymusic.listener.HttpObserver;
import com.ixuea.courses.mymusic.util.ToastUtil;

public class BaseLoginActivity extends BaseTitleActivity {
    protected void login(User user){
        //调用登录接口
        Api.getInstance().login(user)
                .subscribe(new HttpObserver<DetailResponse<Session>>(getMainActivity(),true) {
                    @Override
                    public void onSuccess(DetailResponse<Session> data) {
                        //这里只有登录成功才会调用该方法
                        //如果登录不成功会服务端会返回错误消息
                        ToastUtil.shortSuccessToast("登录成功！");
                        AppContent.getInstance().login(sp,data.getData());
                        startActivityAfterFinishThis(MainActivity.class);
                    }
                });
    }
}
