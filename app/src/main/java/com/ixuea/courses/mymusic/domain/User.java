package com.ixuea.courses.mymusic.domain;

/**
 * 用户模型
 */
public class User extends BaseMode {
    private String phone;
    private String email;
    /**
     * 用户的密码,登录，注册向服务端传递
     */
    private String password;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
