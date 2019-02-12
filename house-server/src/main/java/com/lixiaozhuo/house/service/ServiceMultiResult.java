package com.lixiaozhuo.house.service;

import java.util.List;

/**
 * 服务接口多结果通用结构
 */
public class ServiceMultiResult<T> {
    //结果条数
    private long total;
    //结果集
    private List<T> result;

    public ServiceMultiResult(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * 获取结果个数
     */
    public int getResultSize() {
        if (this.result == null) {
            return 0;
        }
        return this.result.size();
    }
}
