package com.hef.review.designpatterns.creational.singleton;

/**
 * 懒汉式: 用到到时候再创建
 * @Date 2022/10/7
 * @Author lifei
 */
public class Single01 {

    private Single01(){}

    /**
     * 使用volatile的作用：1. 禁止指令重排; 2. 变量不会再多个线程中存在多个副本，直接从主内粗读取
     * （volatile只在jdk1.5之后有用）
     *
     *  instance = new Single01();  会被拆分成三个指令
     *  1. 为 instance分配内存空间；
     *  2. 调用 Single01 构造函数为其初始化；
     *  3. 将instance对象指向分配的内存空间；
     *
     *  指令可以 1-2-3 顺序执行，如果指令重排 可能会以 1-3-2 顺序执行
     *  第一个线程执行 1-3 后，此时instance已经不为null，但还没有初始化，第二个线程抢用instance，就会出错
     */
    private volatile static Single01 instance;

    public static Single01 instance() {
        if (instance==null) {
            synchronized(Single01.class) {
                if (instance==null) {
                    instance = new Single01();
                }
            }
        }
        return instance;
    }
}
