package com.ixuea.courses.mymusic.util;

import android.util.Log;

import com.ixuea.courses.mymusic.BuildConfig;

/**
 * 调试级别日志
 */
public class LogUtil {
    /**
     * 是否是Debug版本
     */
    public final static boolean isDebug = BuildConfig.DEBUG;

    public static void d(String tag, String value){
        if(isDebug){
            Log.d(tag, value);
        }
    }

}
