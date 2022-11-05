package com.hef.review.designpatterns.behavioral.interpreter.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * or 表达式
 * @Date 2022/11/5
 * @Author lifei
 */
public class RuleORExpression implements RuleExpression{

    private List<RuleExpression> ruleExpressionList = new ArrayList<>();

    public RuleORExpression(String expression) {
        String[] subExpressions = expression.trim().split("\\|\\|");
        for (String subExpression : subExpressions) {
            ruleExpressionList.add(new RuleAndExpression(subExpression));
        }
    }

    @Override
    public boolean interpreter(Map<String, Long> state) {
        for (RuleExpression ruleExpression : ruleExpressionList) {
            if (ruleExpression.interpreter(state)) {
                return true;
            }
        }
        return false;
    }
}
