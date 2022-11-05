package com.hef.review.designpatterns.behavioral.interpreter.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * && 表达式
 * @Date 2022/11/5
 * @Author lifei
 */
public class RuleAndExpression implements RuleExpression{

    private List<RuleExpression> ruleExpressionList = new ArrayList<>();

    public RuleAndExpression(String expression) {
        String[] subExpressions = expression.trim().split("&&");
        for (String subExpression : subExpressions) {
            if (subExpression.contains(">")) {
                ruleExpressionList.add(new RuleGTExpression(subExpression));
            } else if (subExpression.contains("<")) {
                ruleExpressionList.add(new RuleLTExpression(subExpression));
            } else if (subExpression.contains("==")) {
                ruleExpressionList.add(new RuleEQExpression(subExpression));
            } else {
                throw new IllegalArgumentException("无效的表达式: " + expression);
            }
        }
    }

    @Override
    public boolean interpreter(Map<String, Long> state) {
        for (RuleExpression ruleExpression : ruleExpressionList) {
            if (!ruleExpression.interpreter(state)) {
                return false;
            }
        }
        return true;
    }
}
