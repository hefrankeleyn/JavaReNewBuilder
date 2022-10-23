package com.hef.review.designpatterns.behavioral.strategy.example02;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public class MapReduceSort implements ISortAlg{
    @Override
    public void sort(String filePath) {
        System.out.println("当文件超级大的时候，使用真正的Map-Reduce排序");
    }
}
