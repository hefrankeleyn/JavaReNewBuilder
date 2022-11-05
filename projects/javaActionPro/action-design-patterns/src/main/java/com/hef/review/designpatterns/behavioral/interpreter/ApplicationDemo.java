package com.hef.review.designpatterns.behavioral.interpreter;

import com.hef.review.designpatterns.behavioral.interpreter.version02.ExpressionInterpreter;

/**
 * 测试自定义的表达式
 * @Date 2022/11/5
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        ExpressionInterpreter interpreter = new ExpressionInterpreter();
        String expression = "3 1 5 + *";
        long res = interpreter.interpreter(expression);
        System.out.println(res);
    }
}
