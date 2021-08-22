package com.hef.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @Date 2021/8/22
 * @Author lifei
 */
public class ReflexDemo {

    public static void main(String[] args) {
        try {
            Class<?> sc = Class.forName("com.hef.domain.Student");
            Constructor<?> constructor = sc.getConstructor();
            Student student = (Student) constructor.newInstance();
            System.out.println(student.age);
            Field nameField = sc.getField("name");
            System.out.println(nameField.get(student));
//            Field ageField = sc.getDeclaredField("age");
//            ageField.setAccessible(true);
//            System.out.println(ageField.get(student));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
