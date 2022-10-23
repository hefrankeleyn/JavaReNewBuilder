package com.hef.review.designpatterns.behavioral.chainofresponsibility.example;

import java.util.Objects;

/**
 * 敏感词过滤
 * @Date 2022/10/23
 * @Author lifei
 */
public abstract class SensitiveWordFilter {

    private SensitiveWordFilter successor;

    public void setSuccessor(SensitiveWordFilter successor) {
        this.successor = successor;
    }

    public final boolean filter(Context context) {
        boolean legal = doFilter(context);
        if (legal && Objects.nonNull(successor)) {
            return successor.filter(context);
        }
        return legal;
    }

    public abstract boolean doFilter(Context context);
}
