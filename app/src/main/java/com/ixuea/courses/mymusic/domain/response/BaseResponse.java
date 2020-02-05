package com.ixuea.courses.mymusic.domain.response;

/**
 * 通用网络请求响应模型
 */
public class BaseResponse {
    /**
     * 状态码
     *
     * 只有发生了错误才会有
     */
    //int与Integer的区别：int是基础数据类型，Integer是类
            //在局部变量中int会有初始值0，Integer的初始值是null
    private Integer status;
    /**
     * 出错的提示信息
     *
     * 发生了错误不一定有
     */
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
