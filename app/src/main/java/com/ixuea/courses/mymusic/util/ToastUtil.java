package com.ixuea.courses.mymusic.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import es.dmoral.toasty.Toasty;

public class ToastUtil {
    private static Context context;

    /**
     * 短时间错误提示，带图标
     * @param strId
     */
    public static void errorShortToast(@StringRes int strId){
        //默认会显示图标
        Toasty.error(context,strId,Toasty.LENGTH_SHORT).show();
    }

    public static void errorShortToast(String mess){
        //默认会显示图标
        Toasty.error(context,mess,Toasty.LENGTH_SHORT).show();
    }

    /**
     * 长时间错误提示，带图标
     * @param strId
     */
    public static void errorLongToast(@StringRes int strId){
        //默认会显示图标
        Toasty.error(context,strId,Toasty.LENGTH_LONG).show();
    }

    /**
     * 短时间成功提示，带图标
     * @param strId
     */
    public static void shortSuccessToast(@StringRes int strId){
        //默认会显示图标
        Toasty.success(context,strId,Toasty.LENGTH_SHORT).show();
    }

    public static void shortSuccessToast(String strId){
        //默认会显示图标
        Toasty.success(context,strId,Toasty.LENGTH_SHORT).show();
    }

    /**
     * 长时间成功提示，带图标
     * @param strId
     */
    public static void longSuccessToast(@StringRes int strId){
        //默认会显示图标
        Toasty.success(context,strId,Toasty.LENGTH_LONG).show();
    }

    public static void init(Context context) {
        ToastUtil.context = context;
    }
}
