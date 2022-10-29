package com.hef.review.designpatterns.behavioral.iterator.impl;

import com.hef.review.designpatterns.behavioral.iterator.MeanItem;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * 煎饼屋迭代器
 * @Date 2022/10/29
 * @Author lifei
 */
public class PancakeHouseMenuIterator implements Iterator<MeanItem> {

    private List<MeanItem> meanItems;
    private int position;

    public PancakeHouseMenuIterator(List<MeanItem> meanItems) {
        this.meanItems = meanItems;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        if (position>=meanItems.size() || meanItems.get(position)==null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public MeanItem next() {
        return meanItems.get(position++);
    }
}
