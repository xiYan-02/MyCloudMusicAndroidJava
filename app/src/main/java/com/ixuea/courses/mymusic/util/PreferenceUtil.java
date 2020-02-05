package com.ixuea.courses.mymusic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.facebook.stetho.common.StringUtil;

public class PreferenceUtil {


    private final String USER_ID = "USER_ID";
    private final String GUIDE_KEY = "SHOW_GUIDE";
    private final String SESSION = "SESSION";
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

    /**
     * 是否已经显示了引导界面
     * @return
     */
    public boolean isShowGuide(){
        return sharedPreferences.getBoolean(GUIDE_KEY,true);
    }

    /**
     * 设置引导界面是否显示
     * @param val
     */
    public void setShowGuide(boolean val){
        sharedPreferences.edit().putBoolean(GUIDE_KEY,val).commit();
    }

    /**
     * 设置登录的Session
     * @param value
     */
    public void setSession(String value){
        put(SESSION,value);
    }

    /**
     * 获取登录的Session
     * @return
     */
    public String getSession(){
        return sharedPreferences.getString(SESSION,null);
    }

    /**
     * 设置登录的ID
     * @param userId
     */
    public void setUserId(String userId){
        put(USER_ID,userId);
    }

    /**
     * 获取登录的Id
     * @return
     */
    public String getUserId(){
        return sharedPreferences.getString(USER_ID,null);
    }

    /**
     * 重构的putString方法
     * @param key
     * @param value
     */
    private void put(String key,String value){
        sharedPreferences.edit().putString(key,value).commit();
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(getSession());
    }
}
