package com.hef.review.designpatterns.behavioral.strategy.example02;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public class QuickSort implements ISortAlg{
    @Override
    public void sort(String filePath) {
        System.out.println("当文件不是很大当时候，使用快速排序");
    }
}
