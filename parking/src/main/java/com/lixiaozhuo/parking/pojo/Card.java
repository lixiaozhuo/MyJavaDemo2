package com.lixiaozhuo.parking.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * IC卡
 */
@Entity
@Table(name="t_card")
public class Card implements Serializable {
    //id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    //对应停车位置
    @OneToOne
    @JoinColumn(name="place_no")
    private Place place;

    //用户名
    @Column(name="name")
    private String name;

    //性别
    @Column(name="gender")
    private String gender;

    //地址
    @Column(name="address")
    private String address;

    //车辆编号
    @Column(name="car_no")
    private String car_no;

    //卡片状态
    @Column(name="state")
    private Boolean state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }


    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
