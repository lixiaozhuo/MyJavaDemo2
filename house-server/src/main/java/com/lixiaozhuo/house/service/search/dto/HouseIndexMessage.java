package com.lixiaozhuo.house.service.search.dto;

/**
 * 房屋索引消息
 */
public class HouseIndexMessage {
    //索引
    public static final String INDEX = "index";
    //删除
    public static final String REMOVE = "remove";
    //最大重试次数
    public static final int MAX_RETRY = 3;

    //房屋id
    private Long houseId;
    //操作
    private String operation;
    //重试次数
    private int retry = 0;


    public HouseIndexMessage() {
    }

    public HouseIndexMessage(Long houseId, String operation, int retry) {
        this.houseId = houseId;
        this.operation = operation;
        this.retry = retry;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
}
