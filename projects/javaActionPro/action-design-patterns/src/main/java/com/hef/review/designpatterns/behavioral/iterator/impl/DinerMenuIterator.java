package com.hef.review.designpatterns.behavioral.iterator.impl;

import com.hef.review.designpatterns.behavioral.iterator.MeanItem;

import java.util.Iterator;

/**
 * @Date 2022/10/29
 * @Author lifei
 */
public class DinerMenuIterator implements Iterator<MeanItem> {

    private MeanItem[] meanItems;
    private int position = 0;

    public DinerMenuIterator(MeanItem[] meanItems) {
        this.meanItems = meanItems;
    }

    @Override
    public boolean hasNext() {
        if (position>=meanItems.length || meanItems[position]==null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public MeanItem next() {
        return meanItems[position++];
    }
}
