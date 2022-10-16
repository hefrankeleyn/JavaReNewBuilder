package com.hef.review.designpatterns.creational.prototype;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2022/10/16
 * @Author lifei
 */
public class PrototypeDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        Map<String, Integer> map02 = new HashMap<>();
        map02.replace("name", 23);
        //
        objectSerialization();
    }

    /**
     * 通过序列化的方式，克隆对象
     */
    private static void objectSerialization() {
        try {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setUserName("小明");
            phoneNumber.setNumbers(new int[]{1, 0, 5, 1, 1});
            // 先进行序列化
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(phoneNumber);
            // 再进行反序列化
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            Object o = oi.readObject();
            System.out.println(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
