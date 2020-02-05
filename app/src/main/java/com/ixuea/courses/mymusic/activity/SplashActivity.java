package com.ixuea.courses.mymusic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.util.PreferenceUtil;

public class SplashActivity extends BaseCommonActivity {

    private int MESS_NEXT = 0x001;

    /**
     * 创建Handler
     *
     * 这样创建有内存泄漏
     * 注意性能优化
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg.what == MESS_NEXT){
                next();
            }

        }
    };

    private void next() {

        if (sp.getInstance(getApplicationContext()).isShowGuide()){
            startActivityAfterFinishThis(GuideActivity.class);
        }else if (sp.isLogin()) {
            //已经登录了

            //跳转到首页
            startActivityAfterFinishThis(MainActivity.class);
        } else {
            //跳转到登录注册界面
            startActivityAfterFinishThis(LoginOrRegisterActivity.class);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initViews() {
        super.initViews();
        //设置全屏
        fullScreen();

        //延时3秒
        //在企业中通常会有很多逻辑处理
        //所以延时时间最好是用3-消耗的的时间,从而保证时间为3秒
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MESS_NEXT);
            }
        },3000);
    }
}
