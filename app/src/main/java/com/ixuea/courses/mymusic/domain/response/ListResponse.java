package com.ixuea.courses.mymusic.domain.response;

import java.util.List;

public class ListResponse<T> extends BaseResponse {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
