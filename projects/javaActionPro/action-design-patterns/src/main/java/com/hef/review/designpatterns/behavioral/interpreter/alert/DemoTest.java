package com.hef.review.designpatterns.behavioral.interpreter.alert;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义一个告警规则：
 * 1. 只包含“||、&&、>、<、==”这五个运算符；
 * 2. “>、<、==”运算符的优先级高于“||、&&”运算符，“&&”运算符优先级高于“||”
 * @Date 2022/11/5
 * @Author lifei
 */
public class DemoTest {

    public static void main(String[] args) {
        String rule = "key1 > 100 && key2 < 30 || key3 < 100 || key4 == 88";
        AlertRuleInterpreter interpreter = new AlertRuleInterpreter(rule);

        Map<String, Long> state = new HashMap<>();
        state.put("key1", 101l);
        state.put("key3", 101l);
        state.put("key4", 81l);

        boolean alert = interpreter.interpreter(state);
        System.out.println(alert);

    }
}
