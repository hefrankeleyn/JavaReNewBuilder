package com.hef.domain;

/**
 * 单例
 * @Date 2021/8/22
 * @Author lifei
 */
public final class SingletonPerson {

    private final static SingletonPerson instance = new SingletonPerson();

    private SingletonPerson() {
        // 预防反射调用
        if (instance!=null) {
            throw new RuntimeException("Cannot create obj by reflex");
        }
    }

    public static SingletonPerson instance() {
        return instance;
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.name);
        System.out.println(student.age);
    }
}
