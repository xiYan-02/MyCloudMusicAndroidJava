package com.ixuea.courses.mymusic.activity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.ixuea.courses.mymusic.util.PreferenceUtil;

/**
 * 通用界面逻辑
 */

public class BaseCommonActivity extends BaseActivity {

    protected PreferenceUtil sp;

    /**
     * 启动界面
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz){
        startActivity(new Intent(getMainActivity(),clazz));
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        sp = PreferenceUtil.getInstance(getMainActivity());
    }

    /**
     * 启动界面并关闭当前界面
     *
     * @param clazz
     */
    protected void startActivityAfterFinishThis(Class<?> clazz){
        startActivity(new Intent(getMainActivity(),clazz));
        finish();
    }

    /**
     * 获取界面
     * @return
     */
    public BaseCommonActivity getMainActivity(){
        return this;
    }

    /**
     * 隐藏虚拟按键并且全屏
     */
    protected void fullScreen(){

        //获取decorView
        View decorView = getWindow().getDecorView();
        //判断版本
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19){
            //11~18版本，Android版本：3.0~4.4
            decorView.setSystemUiVisibility(View.GONE);
        }else{
            //19及以上版本
            //SYSTEM_UI_FLAG_HIDE_NAVIGATION:隐藏导航栏
            //SYSTEM_UI_FLAG_IMMERSIVE_STICKY:从状态栏下拉会半透明悬浮显示一会儿状态栏和导航栏
            //SYSTEM_UI_FLAG_FULLSCREEN:全屏
            int options = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_FULLSCREEN;

            //设置到控件
            decorView.setSystemUiVisibility(options);
        }
    }

    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
