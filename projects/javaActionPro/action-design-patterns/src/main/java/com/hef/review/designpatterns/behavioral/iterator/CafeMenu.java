package com.hef.review.designpatterns.behavioral.iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 咖啡菜单
 * @Date 2022/10/29
 * @Author lifei
 */
public class CafeMenu implements Iterable<MeanItem> {

    private Map<String, MeanItem> meanItems = new HashMap<>();

    public CafeMenu() {
        addItem("Cafe01", "001", true, 5.31);
        addItem("Cafe02", "002", false, 1.31);
        addItem("Cafe03", "003", true, 2.31);
        addItem("Cafe04", "004", false, 3.31);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MeanItem meanItem = new MeanItem(name, description, vegetarian, price);
        meanItems.put(name, meanItem);
    }

    @Override
    public Iterator<MeanItem> iterator() {
        return meanItems.values().iterator();
    }
}
