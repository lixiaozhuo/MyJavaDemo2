package com.lixiaozhuo._01_creating._04_prototype;

import java.util.Date;

/**
 * 原型类(深复制)
 */
public class Sheep2 implements Cloneable {
    private String sName;
    private Date birthday;

    public Sheep2() {
    }


    public Sheep2(String sName, Date birthday) {
        super();
        this.sName = sName;
        this.birthday = birthday;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();  //直接调用object对象的clone()方法！

        //实现深复制
        Sheep2 s = (Sheep2) obj;
        s.birthday = (Date) this.birthday.clone();  //把属性也进行克隆！

        return obj;
    }


    public String getSName() {
        return sName;
    }


    public void setSName(String sName) {
        this.sName = sName;
    }


    public Date getBirthday() {
        return birthday;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


}
