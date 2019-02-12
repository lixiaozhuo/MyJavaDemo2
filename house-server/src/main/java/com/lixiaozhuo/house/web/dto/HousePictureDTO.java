package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 房屋图片信息DTO
 */
public class HousePictureDTO implements Serializable {
    private Long id;
    //对应房屋id
    @JsonProperty(value = "house_id")
    private Long houseId;
    //图片路径
    @JsonProperty(value = "cdn_prefix")
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
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
