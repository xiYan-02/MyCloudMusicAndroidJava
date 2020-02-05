package com.ixuea.courses.mymusic.listener;

import android.text.TextUtils;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseCommonActivity;
import com.ixuea.courses.mymusic.domain.response.BaseResponse;
import com.ixuea.courses.mymusic.util.HttpUtil;
import com.ixuea.courses.mymusic.util.LoadingUtil;
import com.ixuea.courses.mymusic.util.LogUtil;
import com.ixuea.courses.mymusic.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 网络请求Observer
 */
public abstract class HttpObserver<T> extends ObserverAdapter<T> {

    private boolean isShowLoading;
    private BaseCommonActivity activity;


    public HttpObserver(BaseCommonActivity activity,boolean isShowLoading){
        this.activity = activity;
        this.isShowLoading = isShowLoading;
    }

    public HttpObserver(){

    }

    /**
     * 请求成功的抽象方法
     * @param data
     */
    public abstract void onSuccess(T data);

    /**
     * 给子类提供的方法，用于让子类进行自己的错误处理
     * 返回true：子类自己处理了，返回false(默认)：不需要子类处理
     * @param t
     * @param e
     * @return
     */
    public boolean onFailed(T t, Throwable e) {

        return false;
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        if(isSuccess(t)){
            //请求正常
            onSuccess(t);
        }else{
            requestErrorHandler(t,null);
        }

        hideLoad();
    }

    /**
     * 用于判断是否请求成功
     * @param t：请求成功后封装的类
     * @return
     */
    public boolean isSuccess(T t){
        //instanceof:用来测试一个对象是否为一个类的实例
        if(t instanceof BaseResponse){
            return ((BaseResponse) t).getStatus() == null;
        }
        return false;
    }

    /**
     * 用于判断子类是否处理了错误
     * @param t
     * @param e
     */
    public void requestErrorHandler(T t,Throwable e){
        hideLoad();
        if(onFailed(t,e)){
            //回调了请求失败方法
            //并且该方法返回了true

            //返回true就表示外部手动处理错误
            //那我们框架内部就不用做任何事情了
            return;
        }

        HttpUtil.handlerRequestError(t,e);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        LogUtil.d("error", "onError:" + e.getLocalizedMessage());
        //TODO 处理错误
        requestErrorHandler(null,e);
    }

    private void hideLoad(){
        if(isShowLoading) {
            LoadingUtil.hideLoading();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        if(isShowLoading){
            LoadingUtil.showLoading(activity);
        }
    }
}
