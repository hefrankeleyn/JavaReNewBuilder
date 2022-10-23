package com.hef.review.designpatterns.behavioral.chainofresponsibility.example;

/**
 * 性关键词过滤
 * @Date 2022/10/23
 * @Author lifei
 */
public class SexyWordFilter extends SensitiveWordFilter{
    @Override
    public boolean doFilter(Context context) {
        boolean legal = false;
        // 判断是否合法
        return legal;
    }
}
