package com.hef.review.designpatterns.behavioral.template;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;

/**
 * 模版方法模式：子类
 * @Date 2022/10/23
 * @Author lifei
 */
public class CoffeeWithHook extends CaffeineBeverageWithHook {
    @Override
    public void brew() {
        out.println("冲泡咖啡");
    }

    @Override
    public void addCondiments() {
        out.println("添加 牛奶");
    }

    /**
     * 实现钩子方法
     * @return
     */
    @Override
    public boolean customerWantsCondiments() {
        String answer = getUserInput();
        if (StringUtils.startsWithIgnoreCase(answer, "y")) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取用户输入
     * @return
     */
    private String getUserInput() {
        String answer = null;
        out.println("你想添加牛奶配料吗？");
        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inputStreamReader)) {
            answer = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (answer==null) {
            return "no";
        }else {
            return answer;
        }
    }
}
