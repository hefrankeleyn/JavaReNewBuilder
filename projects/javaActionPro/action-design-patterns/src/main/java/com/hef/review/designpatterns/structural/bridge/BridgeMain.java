package com.hef.review.designpatterns.structural.bridge;

/**
 * @Date 2022/10/18
 * @Author lifei
 */
public class BridgeMain {

    public static void main(String[] args) {
        try {
            // 1. JVM查找并加载 MyDriverBean 类； 2. 执行该类的静态代码；
            Class.forName("com.hef.review.designpatterns.structural.bridge.MyDriverBean");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
