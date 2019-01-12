package com.lixiaozhuo._02_structural._04_bridge;


/**
 *桥接模式测试
 */
public class Client {
    public static void main(String[] args) {
        //销售联想的笔记本电脑
        Computer c = new Laptop(new Lenovo());
        c.sale();

        //销售神舟的台式机
        Computer c2 = new Desktop(new ShenZhou());
        c2.sale();
    }
}
