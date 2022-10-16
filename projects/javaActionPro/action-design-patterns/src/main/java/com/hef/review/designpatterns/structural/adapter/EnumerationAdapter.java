package com.hef.review.designpatterns.structural.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * 将迭代器适配成枚举
 * @Date 2022/10/16
 * @Author lifei
 */
public class EnumerationAdapter<T> implements Enumeration<T> {

    private Iterator<T> iterator;

    public EnumerationAdapter(Iterator<T> iterator) {
        this.iterator = iterator;
    }
    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public T nextElement() {
        return iterator.next();
    }
}
