package com.ixuea.courses.mymusic.domain;

import java.util.List;

/**
 * 歌单列表包裹对象
 *
 * 只是用来测试
 */
public class SheetListWrapper {
    /**
     * 歌单列表
     */
    private List<SheetDetail> data;

    public List<SheetDetail> getData() {
        return data;
    }

    public void setData(List<SheetDetail> data) {
        this.data = data;
    }
}
