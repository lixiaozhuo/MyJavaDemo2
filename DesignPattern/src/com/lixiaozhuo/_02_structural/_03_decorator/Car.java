package com.lixiaozhuo._02_structural._03_decorator;

/**
 * ConcreteComponent 具体构件角色(真实对象)
 */
public class Car implements ICar{
    @Override
    public void move() {
        System.out.println("陆地上跑！");
    }
}
