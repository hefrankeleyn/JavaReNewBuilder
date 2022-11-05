package com.hef.review.designpatterns.behavioral.interpreter.version02;

import org.apache.commons.lang3.StringUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date 2022/11/5
 * @Author lifei
 */
public class ExpressionInterpreter {

    private Deque<Expression> numbers = new LinkedList<>();

    public long interpreter(String expression) {
        String[] elements = expression.split(" ");
        int lastNumberIndex = elements.length/2;
        for (int i=0; i<=lastNumberIndex; i++) {
            numbers.add(new NumberExpression(elements[i]));
        }

        for (int i=lastNumberIndex+1; i<elements.length; i++) {
            String operator = elements[i];
            boolean valid = StringUtils.equals(operator, "+") || StringUtils.equals(operator, "-")
                    || StringUtils.equals(operator, "*") || StringUtils.equals(operator, "/");
            if (!valid) {
                throw new IllegalArgumentException("无效的表达式: " + expression);
            }
            Expression expression1 = numbers.pollFirst();
            Expression expression2 = numbers.pollFirst();
            Expression combinedExp;
            if (StringUtils.equals(operator, "+")) {
                combinedExp = new AdditionExpression(expression1, expression2);
            } else if (StringUtils.equals(operator, "-")) {
                combinedExp = new SubtractionExpression(expression1, expression2);
            } else if (StringUtils.equals(operator, "*")) {
                combinedExp = new MultiplicationExpression(expression1, expression2);
            }else {
                combinedExp = new DivisionExpression(expression1, expression2);
            }
            long result = combinedExp.interpreter();
            numbers.addFirst(new NumberExpression(result));
        }

        if (numbers.size()!=1) {
            throw new IllegalArgumentException("无效的表达式： " + expression);
        }
        return numbers.pop().interpreter();
    }
}
