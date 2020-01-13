package com.ixuea.courses.mymusic.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {


    private static final String GUIDE_KEY = "SHOW_GUIDE";
    private final Context context;
    private final String NAME = "ixuea_my_cloud_music";
    private final SharedPreferences sharedPreferences;

    public PreferenceUtil(Context context) {
        this.context = context.getApplicationContext();
        //这样写有内存泄漏,因为当前工具类不会马上释放
        //如果当前工具类引用了界面实例,当界面关闭后,因为界面对应在这里还有引用,所以会导致界面对象不会被释放
        //this.context = context;
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    private static PreferenceUtil instance;

    public static PreferenceUtil getInstance(Context context) {
        if(instance == null){
            instance = new PreferenceUtil(context);
        }
        return instance;
    }

    public boolean isShowGuide(){
        return sharedPreferences.getBoolean(GUIDE_KEY,true);
    }

    public void setShowGuide(boolean val){
        sharedPreferences.edit().putBoolean(GUIDE_KEY,val).commit();
    }
}
