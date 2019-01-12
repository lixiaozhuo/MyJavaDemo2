package com.lixiaozhuo._03_behavioral._08_visitor;

/**
 * 访问者模式测试
 */
public class Client {
    public static void main(String[] args) {
        AccountBook accountBook = new AccountBook();
        //添加两条收入
        accountBook.add(new BillIncome(10000, "卖商品"));
        accountBook.add(new BillIncome(12000, "卖广告位"));
        //添加两条支出
        accountBook.add(new BillConsume(1000, "工资"));
        accountBook.add(new BillConsume(2000, "材料费"));
        //访问者
        ViewerAccountBook boss = new ViewerBoss();
        ViewerAccountBook cpa = new ViewerCPA();

        //两个访问者分别访问账本
        accountBook.show(cpa);

        accountBook.show(boss);
        ((ViewerBoss) boss).getTotalConsume();
        ((ViewerBoss) boss).getTotalIncome();
    }
}
