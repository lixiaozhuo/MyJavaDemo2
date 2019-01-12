package com.lixiaozhuo._01_creating._04_prototype;

import java.io.Serializable;
import java.util.Date;

/**
 * 原型类(浅复制)
 */
public class Sheep1 implements Cloneable, Serializable {

    private String sName;

    private Date birthday;

    public Sheep1() {
    }

    public Sheep1(String sName, Date birthday) {
        super();
        this.sName = sName;
        this.birthday = birthday;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();  //直接调用object对象的clone()方法！
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
