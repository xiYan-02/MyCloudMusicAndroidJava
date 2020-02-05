package com.ixuea.courses.mymusic.api;

import com.ixuea.courses.mymusic.domain.BaseMode;
import com.ixuea.courses.mymusic.domain.Session;
import com.ixuea.courses.mymusic.domain.SheetDetail;
import com.ixuea.courses.mymusic.domain.SheetDetailWrapper;
import com.ixuea.courses.mymusic.domain.SheetListWrapper;
import com.ixuea.courses.mymusic.domain.User;
import com.ixuea.courses.mymusic.domain.response.DetailResponse;
import com.ixuea.courses.mymusic.domain.response.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 利用RXJava实现响应式编程
 * 网络接口配置
 * 之所以调用接口还能返回数据
 * 是因为Retrofit框架内部处理了
 */
public interface Service {

    /**
     * 歌单详情,@GET("v1/sheets/{id}")中的id就是@Path("id")中的id，就是参数
     *
     * @param id
     * @return
     */
    @GET("v1/sheets/{id}")
    Observable<DetailResponse<SheetDetail>> sheetDetail(@Path("id") String id);

    /**
     * 获取全部歌单列表，无参数
     * @return
     */
    @GET("v1/sheets")
    Observable<ListResponse<SheetDetail>> sheets();

    /**
     * 登录
     * @param user
     * @return
     */
    @POST("v1/sessions")
    Observable<DetailResponse<Session>> login(@Body User user);

    /**
     * 注册
     * @param user
     * @return
     */
    @POST("v1/users")
    Observable<DetailResponse<BaseMode>> register(@Body User user);

}
