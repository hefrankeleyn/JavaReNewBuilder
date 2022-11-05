package com.hef.review.designpatterns.behavioral.interpreter.version02;

/**
 * 数值表达式
 * @Date 2022/11/5
 * @Author lifei
 */
public class NumberExpression implements Expression{

    private long number;

    public NumberExpression(long number) {
        this.number = number;
    }

    public NumberExpression(String number) {
        this.number = Long.parseLong(number);
    }

    @Override
    public long interpreter() {
        return number;
    }
}
