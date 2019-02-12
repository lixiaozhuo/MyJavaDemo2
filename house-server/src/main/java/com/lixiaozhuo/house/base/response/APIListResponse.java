package com.lixiaozhuo.house.base.response;

public class APIListResponse extends APIResponse {
    //记录个数
    private Long total;

    public APIListResponse(int code, String message, Object data,Long recordsTotal) {
        super(code,message,data);
        this.total = recordsTotal;
    }

    /**
     * 设置响应
     * @param code 响应码
     * @param message 响应信息
     */
    public static APIListResponse ofMessage(int code,String message){
        return new APIListResponse(code,message,null,null);
    }

    /**
     * 根据状态设置响应
     * @param status 状态
     */
    public static APIListResponse ofStatus(Status status){
        return new APIListResponse(status.getCode(),status.getMessage(),null,null);
    }

    /**
     * 成功响应
     * @param data 响应数据
     */
    public static APIListResponse ofSuccess(Object data,Long recordsTotal){
        return new APIListResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getMessage(),data,recordsTotal);
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
