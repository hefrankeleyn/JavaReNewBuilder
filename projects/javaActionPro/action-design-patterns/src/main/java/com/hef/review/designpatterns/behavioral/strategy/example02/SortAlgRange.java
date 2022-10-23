package com.hef.review.designpatterns.behavioral.strategy.example02;

import com.google.common.collect.Range;

/**
 * 排序算法的最佳使用范围
 * @Date 2022/10/23
 * @Author lifei
 */
public class SortAlgRange {

    private final Range<Long> range;
    private final ISortAlg sortAlg;

    public SortAlgRange(Range<Long> range, ISortAlg sortAlg) {
        this.range = range;
        this.sortAlg = sortAlg;
    }

    public boolean isRange(long size) {
        return range.contains(size);
    }

    public ISortAlg getSortAlg() {
        return sortAlg;
    }
}
