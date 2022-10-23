package com.hef.review.designpatterns.behavioral.strategy.example02;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public class ConcurrentExternalSort implements ISortAlg{
    @Override
    public void sort(String filePath) {
        System.out.println("当文件过大当时候，使用并发当外部排序");
    }
}
