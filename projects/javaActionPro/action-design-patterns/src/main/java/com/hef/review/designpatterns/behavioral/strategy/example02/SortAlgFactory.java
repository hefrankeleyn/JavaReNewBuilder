package com.hef.review.designpatterns.behavioral.strategy.example02;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 算法的工厂模式
 * @Date 2022/10/23
 * @Author lifei
 */
public class SortAlgFactory {

    private static final Map<String, ISortAlg> algs = new HashMap<>();

    static {
        algs.put("QuickSort", new QuickSort());
        algs.put("ExternalSort", new ExternalSort());
        algs.put("ConcurrentExternalSort", new ConcurrentExternalSort());
        algs.put("MapReduceSort", new MapReduceSort());
    }

    public static ISortAlg getSortAlg(String type) {
        if (StringUtils.isBlank(type)) {
            throw new IllegalArgumentException("类型不能为空");
        }
        return algs.get(type);
    }
}
