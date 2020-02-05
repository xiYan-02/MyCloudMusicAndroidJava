package com.ixuea.courses.mymusic.listener;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 通用实现Observer里面的方法
 *
 * 目的是避免每次都要实现所有方法
 */
public class ObserverAdapter<T> implements Observer<T> {
    /**
     * 在请求开始 前 执行
     * @param d
     */
    @Override
    public void onSubscribe(Disposable d) {

    }

    /**
     * 请求成功时执行
     * @param t
     */
    @Override
    public void onNext(T t) {

    }

    /**
     * 发生了错误
     * @param e
     */
    @Override
    public void onError(Throwable e) {

    }

    /**
     * onNext方法后调用
     */
    @Override
    public void onComplete() {

    }
}
