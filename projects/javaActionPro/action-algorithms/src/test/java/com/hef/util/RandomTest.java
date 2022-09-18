package com.hef.util;

import org.junit.Test;

import java.util.Random;

/**
 * @Date 2022/9/13
 * @Author lifei
 */
public class RandomTest {

    @Test
    public void randomIntTest() {
        Random random = new Random(23);
        int n = 10;
        while (n>0) {
            int i = random.nextInt(1);
            System.out.println(i);
            n--;
        }
    }
}
