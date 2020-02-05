package com.ixuea.courses.mymusic.domain.response;

/**
 * 详情网络请求解析类
 *
 * 继承BaseResponse
 * 定义了一个泛型T
 */
public class DetailResponse<T> extends BaseResponse {
    /**
     * 真实数据
     *
     * 类型就是泛型
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
