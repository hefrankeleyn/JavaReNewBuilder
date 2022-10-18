package com.hef.review.designpatterns.structural.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Date 2022/10/16
 * @Author lifei
 */
public class AdapterDemo {

    public static void main(String[] args) {
//        iteratorAdapteeEnumeration();
        // 类适配
        Adapter adapter = new Adapter();
        adapter.b2();
        adapter.b1();
    }

    /**
     * 把迭代器适配成枚举
     */
    private static void iteratorAdapteeEnumeration() {
        ArrayList<String> list = new ArrayList<>();
        list.add("01");
        list.add("03");
        list.add("02");
        EnumerationAdapter<String> adapter = new EnumerationAdapter<>(list.iterator());
        while (adapter.hasMoreElements()) {
            System.out.println(adapter.nextElement());
        }
    }
}
