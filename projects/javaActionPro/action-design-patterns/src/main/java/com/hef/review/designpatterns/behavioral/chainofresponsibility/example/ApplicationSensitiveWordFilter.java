package com.hef.review.designpatterns.behavioral.chainofresponsibility.example;

/**
 * 应用示例：过滤敏感词
 * @Date 2022/10/23
 * @Author lifei
 */
public class ApplicationSensitiveWordFilter {

    public static void main(String[] args) {
        SensitiveWordFilterChain sensitiveWordFilterChain = new SensitiveWordFilterChain();
        sensitiveWordFilterChain.addSensitiveWordFilter(new SexyWordFilter());
        sensitiveWordFilterChain.addSensitiveWordFilter(new PoliticalWordFilter());
        sensitiveWordFilterChain.addSensitiveWordFilter(new AdsWordFilter());

        boolean legal = sensitiveWordFilterChain.filter(new Context());
        if (legal) {
            // 发表
        } else {
            // 不发表
        }

    }
}
