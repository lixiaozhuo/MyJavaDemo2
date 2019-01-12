package com.lixiaozhuo._03_behavioral._08_visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 账本
 */
public class AccountBook {
    //账单
    private List<Bill> bills=new ArrayList<>();

    public void add(Bill bill){
        bills.add(bill);
    }

    //访问账单
    public void show(ViewerAccountBook v){
        for(Bill bill:bills){
            bill.accept(v);
        }
    }
}
