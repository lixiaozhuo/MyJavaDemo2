package com.lixiaozhuo.parking.pojo;

import java.io.Serializable;

/**
 * 查询条件封装
 */
public class Condition implements Serializable {
    //条件标志
    private String key;
    //条件值
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
