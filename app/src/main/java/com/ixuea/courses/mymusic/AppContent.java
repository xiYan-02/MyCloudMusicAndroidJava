package com.ixuea.courses.mymusic;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.ixuea.courses.mymusic.domain.Session;
import com.ixuea.courses.mymusic.domain.event.LoginSuccessEvent;
import com.ixuea.courses.mymusic.util.PreferenceUtil;
import com.ixuea.courses.mymusic.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import es.dmoral.toasty.Toasty;

/**
 * 相当于一个全局的Activity，在整个应用的生命周期中只执行一次
 * 要在Manifest文件的application节点中的name节点中引用
 */
public class AppContent extends MultiDexApplication {
    private static AppContent appContent;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化toast工具类
        Toasty.Config.getInstance().apply();

        ToastUtil.init(getApplicationContext());

        //初始化Stetho抓包
        //使用默认参数初始化
//        Stetho.initializeWithDefaults(this);
    }

    public static AppContent getInstance(){
        if(appContent == null){
            appContent = new AppContent();
        }
        return appContent;
    }

    public void login(PreferenceUtil ps, Session session){
        ps.setSession(session.getSession());
        ps.setUserId(session.getUser());
        //发送通知，关闭登录注册界面
        EventBus.getDefault().post(new LoginSuccessEvent());
        onLogin();
    }

    /**
     * 用于初始化其他登录后的数据
     */
    private void onLogin() {

    }


}
