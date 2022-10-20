package com.hef.review.designpatterns.structural.flyweight;

/**
 * @Date 2022/10/20
 * @Author lifei
 */
public class FlyweightMain {

    public static void main(String[] args) {
        Integer val01 = Integer.valueOf(20);
        Integer val02 = Integer.valueOf(20);
        Integer val03 = new Integer(20);
        System.out.println(val01 == val02); // false
        System.out.println(val01 == val03); // ture

        String str01 = "你好";
        String str02 = "你好";
        String str03 = new String("你好");
        System.out.println(str01==str02); // true
        System.out.println(str01==str03); // false
    }
}