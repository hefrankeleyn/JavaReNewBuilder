package com.hef.desingpatterns;

import org.junit.Test;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class DemoTest {

    @Test
    public void test01() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "-nano: " + System.nanoTime());
//            System.out.println(i + "-millis: " + System.currentTimeMillis());
        }

        for (int i = 0; i < 10; i++) {
//            System.out.println(i + "-nano: " + System.nanoTime());
            System.out.println(i + "-millis: " + System.currentTimeMillis());
        }

    }
}
