package com.lixiaozhuo.house.service.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 地图位置信息
 */
public class MapLocation {
    // 经度
    @JsonProperty("lon")
    private double longitude;
    // 纬度
    @JsonProperty("lat")
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
