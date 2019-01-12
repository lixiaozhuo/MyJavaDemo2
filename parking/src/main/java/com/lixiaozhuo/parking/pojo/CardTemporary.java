package com.lixiaozhuo.parking.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 临时IC卡
 */
@Entity
@Table(name="t_card_temporary")
public class CardTemporary implements Serializable {
    //id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    //对应停车位置
    @OneToOne
    @JoinColumn(name="place_no")
    private Place place;

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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
