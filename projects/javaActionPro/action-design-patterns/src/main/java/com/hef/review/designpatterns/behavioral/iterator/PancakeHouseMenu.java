package com.hef.review.designpatterns.behavioral.iterator;

import com.hef.review.designpatterns.behavioral.iterator.impl.PancakeHouseMenuIterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 煎饼屋的菜单
 * @Date 2022/10/29
 * @Author lifei
 */
public class PancakeHouseMenu implements Iterable<MeanItem> {

    private ArrayList<MeanItem> meanItems;

    public PancakeHouseMenu() {
        meanItems = new ArrayList<>();
        addItem("Pancake01", "001",  false, 2.99);
        addItem("Pancake02", "002",  true, 3.99);
        addItem("Pancake03", "003",  false, 1.99);
        addItem("Pancake04", "004",  true, 5.99);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MeanItem meanItem = new MeanItem(name, description, vegetarian, price);
        meanItems.add(meanItem);
    }

    @Override
    public Iterator<MeanItem> iterator() {
        return new PancakeHouseMenuIterator(meanItems);
    }
}
