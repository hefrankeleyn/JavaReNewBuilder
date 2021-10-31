package com.hef.bit;

/**
 * @Date 2021/10/31
 * @Author lifei
 */
public class Demo01 {

    public static void main(String[] args) {
        int a = 3;
        int b = 6;
        System.out.println(String.format("a:%d, b:%d", a, b));
//        int c = a^b;
//        a = a^c;
//        b = b^c;
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println(String.format("a:%d, b:%d", a, b));
        int c = 5;
        System.out.println(Integer.toBinaryString(c));
        System.out.println(-c);
        System.out.println(Integer.toBinaryString(-c));
    }
}
