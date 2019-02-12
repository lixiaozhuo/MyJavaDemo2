package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 用户基本信息DTO
 */
public class UserDTO implements Serializable {
    //id
    private Long id;
    //用户名
    private String name;
    //头像
    private String avatar;
    //电话号码
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    //上次登录时间
    @JsonProperty(value = "last_login_time")
    private String lastLoginTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
