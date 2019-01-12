package com.lixiaozhuo.parking.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 位置
 */
@Entity
@Table(name="t_place")
public class Place implements Serializable {
    //id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    //车位编号
    @Column(name="no")
    private String no;
    //区域
    @Column(name="section")
    private String section;
    //状态
    @Column(name="state")
    private Boolean state;
    //备注
    @Column(name="tag")
    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
