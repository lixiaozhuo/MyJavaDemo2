package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 区域地址信息实体
 */
public class SupportAddressDTO  implements Serializable {
    //id
    private Long id;
    //上一级行政单位
    @JsonProperty(value = "belong_to")
    private String belongTo;
    //行政单位英文名缩写
    @JsonProperty(value = "en_name")
    private String enName;
    //行政单位中文名
    @JsonProperty(value = "cn_name")
    private String cnName;
    //行政级别 市-city 地区-region
    private String level;
    //地图经度
    private double mapLongitude;
    //地图纬度
    private double mapLatitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getMapLongitude() {
        return mapLongitude;
    }

    public void setMapLongitude(double mapLongitude) {
        this.mapLongitude = mapLongitude;
    }

    public double getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(double mapLatitude) {
        this.mapLatitude = mapLatitude;
    }
}
