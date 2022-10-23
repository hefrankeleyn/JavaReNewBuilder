package com.hef.review.designpatterns.behavioral.chainofresponsibility.example;

/**
 * 广告法敏感词过滤
 * @Date 2022/10/23
 * @Author lifei
 */
public class AdsWordFilter extends SensitiveWordFilter{
    @Override
    public boolean doFilter(Context context) {
        boolean legal = false;
        // 看是否符合广告法
        return legal;
    }
}
