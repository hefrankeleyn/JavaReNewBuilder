package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl01;

/**
 * 使用举例
 * @Date 2022/10/23
 * @Author lifei
 */
public class Application {

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new AHandler());
        handlerChain.addHandler(new BHandler());
        handlerChain.handle();
    }
}
