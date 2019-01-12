package com.lixiaozhuo._03_behavioral._08_visitor;

/**
 * 访问者:会计
 */
public class ViewerCPA implements ViewerAccountBook {
    @Override
    public void view(BillConsume bill) {
        if(bill.getItem().equals("工资")){
            System.out.println("检查工资消费是否上税");
        }
    }

    @Override
    public void view(BillIncome bill) {
        System.out.println("检查收入是否上税");
    }
}
