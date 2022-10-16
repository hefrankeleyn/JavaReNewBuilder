package com.hef.review.designpatterns.structural.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @Date 2022/10/16
 * @Author lifei
 */
public class EnumerationIterator<T> implements Iterator<T> {

    private Enumeration<T> enumeration;

    public EnumerationIterator(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
