package com.lixiaozhuo._03_behavioral._08_visitor;

/**
 * 消费账单
 */
public class BillConsume implements Bill{
    //金额
    private double amount;
    //条目
    private String item;

    public BillConsume(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public void accept(ViewerAccountBook v) {
        v.view(this);
    }
}
