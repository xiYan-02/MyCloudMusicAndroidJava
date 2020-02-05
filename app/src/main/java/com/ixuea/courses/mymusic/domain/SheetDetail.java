package com.ixuea.courses.mymusic.domain;

/**
 * 歌单详情模型
 */
public class SheetDetail extends BaseMode{

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String banner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
