package com.ixuea.courses.mymusic.util;

import android.text.TextUtils;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.domain.response.BaseResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public class HttpUtil {
    public static void handlerRequestError(Object data,Throwable e){
        //判断错误类型
        if(e != null){
            //这是服务器错误的情况
            if (e instanceof UnknownHostException) {
                ToastUtil.errorShortToast(R.string.not_fond_net);
            }else if (e instanceof ConnectException) {//没网等情况
                ToastUtil.errorShortToast(R.string.connect_exception);
            }else if(e instanceof SocketTimeoutException){
                ToastUtil.errorShortToast(R.string.socket_timeout_exception);
            }else if (e instanceof HttpException){
                HttpException exception = (HttpException) e;
                int code = exception.code();
                if (code == 401) {
                    ToastUtil.errorShortToast(R.string.net_code_401);
                } else if (code == 403) {
                    ToastUtil.errorShortToast(R.string.net_code_403);
                } else if (code == 404) {
                    ToastUtil.errorShortToast(R.string.net_code_404);
                } else if (code >= 500) {
                    ToastUtil.errorShortToast(R.string.net_code_500);
                } else {
                    ToastUtil.errorShortToast(R.string.net_code_other);
                }
            } else{
                ToastUtil.errorShortToast(R.string.net_code_other);
            }
        }else{
            //这是有服务器返回错误消息的情况
            BaseResponse response= (BaseResponse) data;
            //如果消息为空则说明
            if(TextUtils.isEmpty(response.getMessage())){
                ToastUtil.errorShortToast(R.string.net_code_other);
            }else{
                //直接吐司出服务器返回的错误信息
                ToastUtil.errorShortToast(response.getMessage());
            }
        }
    }
}
