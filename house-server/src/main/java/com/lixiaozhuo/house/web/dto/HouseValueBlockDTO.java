package com.lixiaozhuo.house.web.dto;

/**
 * 房屋数据块DTO
 */
public class HouseValueBlockDTO {
    //区间key
    private String key;
    //最小值
    private int min;
    //最大值
    private int max;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
