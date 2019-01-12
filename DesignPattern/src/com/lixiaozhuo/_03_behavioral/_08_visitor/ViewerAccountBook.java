package com.lixiaozhuo._03_behavioral._08_visitor;

/**
 * 访问者接口
 */
public interface ViewerAccountBook {
    //访问消费账单
    void view(BillConsume bill);
    //访问收入账单
    void view(BillIncome bill);
}
