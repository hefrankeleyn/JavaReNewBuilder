package com.hef.review.designpatterns.behavioral.strategy.example02;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 算法的工厂模式
 * @Date 2022/10/23
 * @Author lifei
 */
public class SortAlgRangeFactory {

    private static final long GB = 1024 * 1024 * 1024;

    private static final List<SortAlgRange> sortAlgRanges = new ArrayList<>();

    static {
        sortAlgRanges.add(new SortAlgRange(Range.<Long>closed(0l, 6*GB), new QuickSort()));
        sortAlgRanges.add(new SortAlgRange(Range.<Long>openClosed(6*GB, 10 * GB), new ExternalSort()));
        sortAlgRanges.add(new SortAlgRange(Range.<Long>openClosed(10 * GB, 100 * GB), new ConcurrentExternalSort()));
        sortAlgRanges.add(new SortAlgRange(Range.<Long>greaterThan(100 * GB), new MapReduceSort()));
    }

    public static ISortAlg getSortAlg(long size) {
        for (SortAlgRange sortAlgRange : sortAlgRanges) {
            if (sortAlgRange.isRange(size)) {
                return sortAlgRange.getSortAlg();
            }
        }
        return sortAlgRanges.get(sortAlgRanges.size()-1).getSortAlg();
    }

    public static void main(String[] args) {
        for (SortAlgRange sortAlgRange : sortAlgRanges) {
            System.out.println(sortAlgRange);
        }
    }
}
