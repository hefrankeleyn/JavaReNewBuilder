package com.hef.review.designpatterns.behavioral.interpreter.version01;

import org.apache.commons.lang3.StringUtils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 未使用解释器模式
 *   假设我们定义了一个新的加减乘除计算“语言”，语法规则如下：
 *   1. 运算符只包含加、减、乘、除，并且没有优先级的概念；
 *   2. 表达式（也就是前面提到的“句子”）中，先书写数字，后书写运算符，空格隔开；
 *   3. 按照先后顺序，取出两个数字和一个运算符计算结果，结果重新放入数字的最头部位置，
 *      循环上述过程，直到只剩下一个数字，这个数字就是表达式最终的计算结果。
 * @Date 2022/11/5
 * @Author lifei
 */
public class ExpressionInterpreter {

    public long interpreter(String expression) {
        Deque<Long> deque = new LinkedList<>();
        String[] elements = expression.split(" ");
        int lastNumIndex = elements.length/2;
        // 拿到数字
        for (int i=0; i<=lastNumIndex; i++) {
            deque.add(Long.parseLong(elements[i]));
        }
        // 获取运算符
        for (int i=lastNumIndex+1; i<elements.length; i++) {
            String operator = elements[i];
            boolean valid = StringUtils.equals(operator, "+") || StringUtils.equals(operator, "-")
                    || StringUtils.equals(operator, "*") || StringUtils.equals(operator, "/");
            if (!valid) {
                throw new IllegalArgumentException("无效的表达式：" + expression);
            }
            long firstVal =  deque.pollFirst();
            long secondVal = deque.pollFirst();
            long res = 0;
            if (StringUtils.equals(operator, "+")) {
                res = firstVal + secondVal;
            }else if (StringUtils.equals(operator, "-")) {
                res = firstVal - secondVal;
            }else if (StringUtils.equals(operator, "*")) {
                res = firstVal * secondVal;
            } else {
                res = firstVal/secondVal;
            }
            deque.addFirst(res);
        }

        if (deque.size()!=1) {
            throw new RuntimeException("无效的表达式：" + expression);
        }
        return deque.pop();
    }
}
