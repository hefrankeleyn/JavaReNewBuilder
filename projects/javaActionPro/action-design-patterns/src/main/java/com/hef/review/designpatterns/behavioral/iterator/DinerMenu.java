package com.hef.review.designpatterns.behavioral.iterator;

import com.hef.review.designpatterns.behavioral.iterator.impl.DinerMenuIterator;

import java.util.Iterator;

/**
 * 晚餐菜单
 * 为了便利菜单，要返回一个迭代器，Iterable 里面定义了返回迭代器的方法
 * @Date 2022/10/29
 * @Author lifei
 */
public class DinerMenu implements Iterable<MeanItem>{

    private static final int MAX_ITEMS = 6;
    private int numberOfItems = 0;
    private MeanItem[] meanItems;

    public DinerMenu() {
        this.meanItems = new MeanItem[MAX_ITEMS];
        addItem("Diner01", "001",  false, 2.99);
        addItem("Diner02", "002",  true, 3.99);
        addItem("Diner03", "003",  false, 1.99);
        addItem("Diner04", "004",  true, 5.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MeanItem meanItem = new MeanItem(name, description, vegetarian, price);
        meanItems[numberOfItems++] = meanItem;
    }

    @Override
    public Iterator<MeanItem> iterator() {
        return new DinerMenuIterator(meanItems);
    }
}
