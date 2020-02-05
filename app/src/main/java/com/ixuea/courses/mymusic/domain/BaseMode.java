package com.ixuea.courses.mymusic.domain;

import java.io.Serializable;

/**
 * 所有模型的父类
 */
public class BaseMode implements Serializable {
    /**
     * ID
     */
    private String id;
    /**
     * 创建时间
     */
    private String created_at;
    /**
     * 更新时间
     */
    private String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
