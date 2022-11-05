package com.hef.review.designpatterns.behavioral.interpreter.version02;

/**
 * 除法表达式
 * @Date 2022/11/5
 * @Author lifei
 */
public class DivisionExpression implements Expression {

    private Expression expression1;
    private Expression expression2;

    public DivisionExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public long interpreter() {
        return expression1.interpreter() / expression2.interpreter();
    }
}
