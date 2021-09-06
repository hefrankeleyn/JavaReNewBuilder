package com.hef.algorithm;

/**
 * 使用数组创建一个循环链表
 * @Date 2021/9/6
 * @Author lifei
 */
public class MyQueue<T> {

    private T[] a;
    private int i;
    private int j;
    private int capacity;

    private boolean isEmpty;

    private MyQueue(int n) {
        a = (T[])new Object[n];
        capacity = n;
    }

    public void insert(T t) {
        if (i==capacity  && !isEmpty) {
            throw new RuntimeException("full...");
        }
        i = i % capacity;
        a[i++] = t;
        if (i == j) {
            isEmpty = false;
        }
    }

    public T pop() {
        if (i==j && isEmpty) {
            throw new RuntimeException("not item....");
        }
        j = j % capacity;
        T t = a[j++];
        if (i==j) {
            isEmpty = true;
        }
        return t;
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>(5);
        for (int n=0; n<10; n++) {
            for (int i = 0; i < 5; i++) {
                myQueue.insert(i);
            }
            for (int i = 0; i < 5; i++) {
                Integer v = myQueue.pop();
                System.out.print(v + ", ");
            }
            System.out.println();
        }



    }
}
