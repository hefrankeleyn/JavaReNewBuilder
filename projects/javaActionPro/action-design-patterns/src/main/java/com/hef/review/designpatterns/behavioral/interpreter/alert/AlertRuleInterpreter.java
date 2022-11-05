package com.hef.review.designpatterns.behavioral.interpreter.alert;

import java.util.Map;

/**
 * @Date 2022/11/5
 * @Author lifei
 */
public class AlertRuleInterpreter {

    private RuleExpression ruleExpression;

    // key1 > 100 && key2 <1000 || key3 == 200
    public AlertRuleInterpreter(String ruleExpression) {
        this.ruleExpression = new RuleORExpression(ruleExpression);
    }

    public boolean interpreter(Map<String, Long> state) {
        return this.ruleExpression.interpreter(state);
    }
}
