package com.ixuea.courses.mymusic.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ixuea.courses.mymusic.domain.BaseMode;
import com.ixuea.courses.mymusic.domain.Session;
import com.ixuea.courses.mymusic.domain.SheetDetail;
import com.ixuea.courses.mymusic.domain.User;
import com.ixuea.courses.mymusic.domain.response.DetailResponse;
import com.ixuea.courses.mymusic.domain.response.ListResponse;
import com.ixuea.courses.mymusic.util.Constant;
import com.ixuea.courses.mymusic.util.LogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求接口包装类
 * 对外部提供一个和框架无关的接口
 */
public class Api {

    private static Api instance;
    private final Service service;

    public Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(LogUtil.isDebug){
            //第三方框架，OKHttp的interceptor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            //Level枚举值
            //      None：默认，不打印日志
            //      BASIC：只打印简单的请求信息，如请求地址、参数、返回的状态码
            //      HEADERS：除了BASIC的所有之外还打印了请求头
            //      BODY：除了HEADERS的所有之外还打印了返回的数据
            interceptor.level(HttpLoggingInterceptor.Level.BASIC);
            //添加OKHttpInterceptor
            builder.addInterceptor(interceptor);
            //添加Stetho
            //builder.addNetworkInterceptor(new StethoInterceptor());
        }

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
        service = retrofit.create(Service.class);
    }

    /**
     * 返回当前对象的唯一实例
     *
     * 单例设计模式
     * 由于移动端很少有高并发
     * 所以这个就是简单判断
     * @return
     */
    public static Api getInstance() {
        if(instance == null){
            instance = new Api();
        }
        return instance;
    }

    /**
     * 根据Id查询唯一的歌单
     * @param id
     * @return
     */
    public Observable<DetailResponse<SheetDetail>> sheetDetail(String id){
        return service.sheetDetail(id)
                .subscribeOn(Schedulers.io())//表示把网络请求放到子线程
                .observeOn(AndroidSchedulers.mainThread());//表示在主线程中进行操作
    }

    /**
     * 查询全部歌单，无参数
     * @return
     */
    public Observable<ListResponse<SheetDetail>> sheets(){
        return service.sheets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    public Observable<DetailResponse<Session>> login(User user){
        return service.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public Observable<DetailResponse<BaseMode>> register(User user){
        return service.register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
