package com.lixiaozhuo._02_structural._01_adapter;

/**
 * 适配器模式客户端类
 *
 * @author Administrator
 */
public class Client {

    public void test(Target t) {
        t.handleReq();
    }

    public static void main(String[] args) {
        //客户端
        Client c = new Client();
        //目标对象
        Adaptee a = new Adaptee();
        //类适配器方式(继承)
        //Target t = new Adapter1();

        //对象适配器(组合)
        Target t = new Adapter2(a);

        c.test(t);

    }

}
