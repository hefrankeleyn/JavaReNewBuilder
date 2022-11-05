package com.hef.review.designpatterns.behavioral.interpreter.alert;

import java.util.Map;

public interface RuleExpression {

    boolean interpreter(Map<String, Long> state);
}
