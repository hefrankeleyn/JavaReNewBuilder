package com.hef.review.designpatterns.behavioral.chainofresponsibility.example;

import java.util.Objects;

/**
 * 敏感词过滤链
 * @Date 2022/10/23
 * @Author lifei
 */
public class SensitiveWordFilterChain {
    private SensitiveWordFilter head, tail;

    public void addSensitiveWordFilter(SensitiveWordFilter sensitiveWordFilter) {
        sensitiveWordFilter.setSuccessor(null);
        if (Objects.nonNull(head)) {
            this.head = sensitiveWordFilter;
            this.tail = sensitiveWordFilter;
            return;
        }
        this.tail.setSuccessor(sensitiveWordFilter);
        this.tail = sensitiveWordFilter;
    }

    public boolean filter(Context context) {
        boolean legal = true;
        if (Objects.nonNull(head)) {
            legal = head.filter(context);
        }
        return legal;
    }

}
