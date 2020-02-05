package com.ixuea.courses.mymusic.activity;

import android.os.Bundle;
import android.widget.Button;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.api.Api;
import com.ixuea.courses.mymusic.domain.event.LoginSuccessEvent;
import com.ixuea.courses.mymusic.listener.HttpObserver;
import com.ixuea.courses.mymusic.api.Service;
import com.ixuea.courses.mymusic.domain.SheetDetail;
import com.ixuea.courses.mymusic.domain.response.DetailResponse;
import com.ixuea.courses.mymusic.domain.response.ListResponse;
import com.ixuea.courses.mymusic.util.Constant;
import com.ixuea.courses.mymusic.util.LogUtil;
import com.ixuea.courses.mymusic.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginOrRegisterActivity extends BaseCommonActivity {

    @BindView(R.id.btn_login)
     Button btn_login;
    @BindView(R.id.btn_register)
     Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
    }

    @Override
    protected void initViews() {
        super.initViews();

        //状态栏透明
        lightStatusBar();

    }

    @OnClick(R.id.btn_login)
    public void onLoginClick(){
        startActivity(LoginActivity.class);
    }

    public void testMyObserver(){
        Api.getInstance()
                .sheetDetail("1")
                .subscribe(new HttpObserver<DetailResponse<SheetDetail>>(getMainActivity(),true) {
                    @Override
                    public void onSuccess(DetailResponse<SheetDetail> data) {
                        //请求成功
                        LogUtil.d("eeee","<<<<<<<<<>>>>>>>>>>>>>:" + data.getData().getTitle());
                    }

                    @Override
                    public boolean onFailed(DetailResponse<SheetDetail> sheetDetailDetailResponse, Throwable e) {
                        ToastUtil.errorShortToast("这是自定义的错误！");
                        return true;
                    }
                });
    }

    /**
     * 最初的retrofit
     */
    public void testNET(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                //让Retrofit使用OkHttp请求网络
                .client(builder.build())
                //这里就是配置API地址
                .baseUrl(Constant.ENDPOINT)
                //添加适配RxJava，让Retrofit配合工作
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //自动JSON转换
                //包括请求参数和相应
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);

        //执行顺序：可以用来实现加载对话框
        //请求成功：onSubscribe --> onNext --> onComplete
        //请求失败：onSubscribe --> onError

        service.sheetDetail("1")
                //固定写法
                .subscribeOn(Schedulers.io())//表示把网络请求放到子线程
                .observeOn(AndroidSchedulers.mainThread())//表示在主线程中进行操作
                .subscribe(new Observer<DetailResponse<SheetDetail>>() {//回调
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailResponse<SheetDetail> sheetDetailWrapper) {
                        //请求成功
                        LogUtil.d("fffffffff","+++++++++++request sheet detail success:"+sheetDetailWrapper.getData().getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求失败
                        LogUtil.d("fffffffffffff","request sheet detail failed:"+e.getLocalizedMessage());

                        //判断错误类型
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick(){
        startActivity(RegisterActivity.class);
    }



    @Override
    protected void initDatum() {
        super.initDatum();
        //注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        //反注册,防止内存泄漏
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //接受来自登录界面登录成功时的通知，用来关闭界面
    //@Subscribe(threadMode = ThreadMode.MAIN)：表示在主线程执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccessEvent(LoginSuccessEvent event){
        finish();
    }

}
