package com.lixiaozhuo.parking.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 临时停车
 */
@Entity
@Table(name="t_park_temporary")
public class ParkTemporary implements Serializable {
    //id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    //临时卡号
    @Column(name="card_id")
    private Integer card_id;

    //车位编号
    @Column(name="place_no")
    private String place_no;

    //汽车编号
    @Column(name="car_no")
    private String car_no;

    //进入时间
    @Column(name="entry_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date entry_time;

    //离开时间
    @Column(name="leave_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date leave_time;

    //花费
    @Column(name="cost")
    private Integer cost;

    //单价
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getPlace_no() {
        return place_no;
    }

    public void setPlace_no(String place_no) {
        this.place_no = place_no;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
