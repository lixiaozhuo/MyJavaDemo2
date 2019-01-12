package com.lixiaozhuo._03_behavioral._08_visitor;

/**
 * 账单接口
 */
public interface Bill {
    void accept(ViewerAccountBook v);
}
