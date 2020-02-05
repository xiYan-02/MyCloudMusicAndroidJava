package com.ixuea.courses.mymusic.domain;

/**
 * 登录后模型
 */
public class Session {
    /**
     * 用户Id
     */
    private String user;

    /**
     * 登录后的Session
     */
    private String session;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
