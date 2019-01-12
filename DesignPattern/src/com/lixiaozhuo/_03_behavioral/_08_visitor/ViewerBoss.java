package com.lixiaozhuo._03_behavioral._08_visitor;

/**
 * 访问者:老板
 */
public class ViewerBoss implements ViewerAccountBook {
    private double totalConsume=0;
    private double totalIncome=0;

    @Override
    public void view(BillConsume bill) {
        totalConsume+=bill.getAmount();
    }

    @Override
    public void view(BillIncome bill) {
        totalIncome+=bill.getAmount();
    }

    public double getTotalConsume() {
        System.out.println("老板花了"+totalConsume);
        return totalConsume;
    }

    public double getTotalIncome() {
        System.out.println("老板赚了"+totalIncome);
        return totalIncome;
    }
}
