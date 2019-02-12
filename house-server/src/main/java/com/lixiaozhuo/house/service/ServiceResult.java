package com.lixiaozhuo.house.service;

/**
 * 服务接口单结果通用结构
 */
public class ServiceResult<T> {
    //标志是否成功
    private boolean success;
    //结果信息
    private String message;
    //结果数据
    private T result;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ServiceResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /**
     * 设置结果集
     * @param success 是否成功
     * @param message 结果信息
     */
    public static <T> ServiceResult<T> ofMessage(boolean success, String message){
        return new ServiceResult<>(success,message);
    }

    /**
     *  构建成功结果
     */
    public static <T> ServiceResult<T> ofSuccess() {
        return new ServiceResult<>(true,"成功");
    }

    /**
     * 根据结果构建成功结果
     */
    public static <T> ServiceResult<T> ofSuccess(T result) {
        ServiceResult<T> serviceResult = new ServiceResult<>(true,"成功");
        serviceResult.setResult(result);
        return serviceResult;
    }

    /**
     * 构建未发现结果
     */
    public static <T> ServiceResult<T> notFound() {
        return new ServiceResult<>(false, Message.NOT_FOUND.getValue());
    }

    /**
     * 构建未登录结果
     */
    public static <T> ServiceResult<T> notLogin() {
        return new ServiceResult<>(false, Message.NOT_LOGIN.getValue());
    }

    /**
     * 结果信息
     */
    public enum Message {
        //未发现数据
        NOT_FOUND("未发现数据"),
        //未登录
        NOT_LOGIN("用户未登录");

        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
