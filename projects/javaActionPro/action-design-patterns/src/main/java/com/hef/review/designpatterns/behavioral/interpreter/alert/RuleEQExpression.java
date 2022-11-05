package com.hef.review.designpatterns.behavioral.interpreter.alert;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 等于表达式
 * @Date 2022/11/5
 * @Author lifei
 */
public class RuleEQExpression implements RuleExpression{

    private String key;
    private Long value;

    public RuleEQExpression(String expression) {
        String[] elements = expression.trim().split("\\s+");
        if (elements.length!=3 && !StringUtils.equals(elements[1], "==")) {
            throw new IllegalArgumentException("无效的表达式： " + expression);
        }
        this.key = elements[0];
        this.value = Long.parseLong(elements[2]);
    }

    @Override
    public boolean interpreter(Map<String, Long> state) {
        if (!state.containsKey(key)) {
            return false;
        }
        return state.get(key) == value;
    }
}
