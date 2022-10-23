package com.hef.review.designpatterns.behavioral.chainofresponsibility.example;

/**
 * 政治敏感词过滤
 * @Date 2022/10/23
 * @Author lifei
 */
public class PoliticalWordFilter extends SensitiveWordFilter{

    @Override
    public boolean doFilter(Context context) {
        boolean legal = false;
        // 过滤政治敏感词
        return legal;
    }
}
