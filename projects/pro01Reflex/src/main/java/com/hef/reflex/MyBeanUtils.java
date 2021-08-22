package com.hef.reflex;

import com.hef.domain.Person;
import com.hef.domain.Person1;

import java.lang.reflect.Field;

/**
 * @Date 2021/8/22
 * @Author lifei
 */
public class MyBeanUtils {

    public static void main(String[] args) {
        Person person = new Person();
        Person1 person1 = new Person1();
        System.out.println(person);
        System.out.println(person1);
        copyBean(person, person1);
        System.out.println(person1);
    }

    public static void copyBean(Object source, Object target) {
        try{
            Class<?> sc = source.getClass();
            Class<?> tc = target.getClass();
            Field[] sFields = sc.getDeclaredFields();
            Field[] tFields = tc.getDeclaredFields();
            for (Field field: sFields) {
                for (Field tf: tFields) {
                    if (field.getName().equals(tf.getName())) {
                        field.setAccessible(true);
                        tf.setAccessible(true);
                        tf.set(target, field.get(source));
                    }
                }
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
