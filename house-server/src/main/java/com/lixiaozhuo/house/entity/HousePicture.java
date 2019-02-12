package com.lixiaozhuo.house.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房屋图片信息实体
 */
@Entity
@Table(name = "house_picture")
public class HousePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //对应房屋id
    @Column(name = "house_id")
    private Long houseId;
    //图片路径
    @Column(name = "cdn_prefix")
    private String cdnPrefix;
    //文件名
    private String path;
    //宽
    private int width;
    //高
    private int height;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
