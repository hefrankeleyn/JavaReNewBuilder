package com.hef.review.designpatterns.behavioral.chainofresponsibility.impl02;

/**
 * 使用举例
 * @Date 2022/10/23
 * @Author lifei
 */
public class Application {

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new HandlerA());
        handlerChain.addHandler(new HandlerB());
        handlerChain.handle();
    }
}
