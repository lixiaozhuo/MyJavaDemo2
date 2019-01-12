package com.lixiaozhuo.parking.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户
 */
@Entity
@Table(name="t_user")
public class User implements Serializable {
    //id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    //手机号
    @Column(name="phone",unique=true)
    private String phone;

    //密码
    @Column(name="password")
    private String password;

    //用户名
    @Column(name="name")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
