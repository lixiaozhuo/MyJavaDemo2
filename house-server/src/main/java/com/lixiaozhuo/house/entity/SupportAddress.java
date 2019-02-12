package com.lixiaozhuo.house.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 支持地址信息实体
 */
@Entity
@Table(name = "support_address")
public class SupportAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 上一级行政单位
    @Column(name = "belong_to")
    private String belongTo;
    //行政单位英文名缩写
    @Column(name = "en_name")
    private String enName;
    //行政单位中文名
    @Column(name = "cn_name")
    private String cnName;
    //行政级别 市-city 地区-region
    private String level;
    //地图经度
    @Column(name = "map_lng")
    private double mapLongitude;
    //地图纬度
    @Column(name = "map_lat")
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

    /**
     * 行政级别定义
     */
    public enum Level {
        //城市
        CITY("city"),
        //地区
        REGION("region");

        private String value;

        Level(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        /**
         * 查找值对应行政级别
         */
        public static Level of(String value) {
            //遍历查找
            for (Level level : Level.values()) {
                if (level.getValue().equals(value)) {
                    return level;
                }
            }
            //找不到对应行政级别
            throw new IllegalArgumentException();
        }
    }
}
