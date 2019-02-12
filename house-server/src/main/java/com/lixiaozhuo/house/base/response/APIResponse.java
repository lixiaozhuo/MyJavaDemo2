package com.lixiaozhuo.house.base.response;

/**
 * API响应格式
 */
public class APIResponse {
    //响应码
    private int code;
    //响应信息
    private String message;
    //数据
    private Object data;
    //是否存在更多数据
    private boolean more;

    public APIResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public APIResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    /**
     * 设置响应
     * @param code 响应码
     * @param message 响应信息
     */
    public static APIResponse ofMessage(int code,String message){
        return new APIResponse(code,message,null);
    }

    /**
     * 根据状态设置响应
     * @param status 状态
     */
    public static APIResponse ofStatus(Status status){
        return new APIResponse(status.getCode(),status.getMessage(),null);
    }

    /**
     * 成功响应
     * @param data 响应数据
     */
    public static APIResponse ofSuccess(Object data){
        return new APIResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getMessage(),data);
    }
    /**
     * 成功响应
     */
    public static APIResponse ofSuccess(){
        return new APIResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getMessage(),null);
    }

    /**
     * 状态
     */
    public enum Status{
        //成功:200
        SUCCESS(200,"OK"),
        //错误请求:400
        BAD_REQUEST(400, "错误请求"),
        //未发现:404
        NOT_FOUND(404, "未发现资源"),
        //未知错误:500
        INTERNAL_SERVER_ERROR(500, "未知错误"),
        //未登录
        NOT_LOGIN(50000, "未登录"),
        //无效参数
        NOT_VALID_PARAM(50005, "无效参数"),
        //无权限
        NOT_SUPPORTED_OPERATION(50006, "无权限");


        //响应码
        private int code;
        //响应信息
        private String message;

        Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }}

        public static Status of(int code){
            //遍历状态
            for (Status status : Status.values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            //未知错误:500
            return Status.INTERNAL_SERVER_ERROR;
        }

}
