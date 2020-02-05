package com.ixuea.courses.mymusic.util;

import android.app.Activity;
import android.app.ProgressDialog;

public class LoadingUtil {

    private static ProgressDialog progressDialog;

    public static void showLoading(Activity mainActivity) {
        showLoading(mainActivity,"拼命加载中...");
    }
    public static void showLoading(Activity mainActivity,String msg) {
        //如果activity为空或者activity已经销毁就返回
        if(mainActivity == null || mainActivity.isFinishing()){
            return;
        }

        //如果当前正在显示progressDialog，就返回
        if(progressDialog != null){
            return;
        }

        progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("请稍等");
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public static void hideLoading(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.hide();
            progressDialog = null;
        }
    }



}
