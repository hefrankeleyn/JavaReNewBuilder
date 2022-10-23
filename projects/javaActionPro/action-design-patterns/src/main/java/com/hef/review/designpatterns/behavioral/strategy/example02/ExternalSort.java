package com.hef.review.designpatterns.behavioral.strategy.example02;

/**
 * @Date 2022/10/23
 * @Author lifei
 */
public class ExternalSort implements ISortAlg{
    @Override
    public void sort(String filePath) {
        System.out.println("当文件比较大当时候，使用外部排序");
    }
}
