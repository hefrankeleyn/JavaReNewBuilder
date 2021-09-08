package com.hef.queue;

/**
 * @Date 2021/9/8
 * @Author lifei
 */
public class MyCycleQueue<T> {

    private T[] a;
    private int capacity;
    private int pushIndex;
    private int takeIndex;
    private boolean isEmpty;

    public MyCycleQueue(int capacity) {
        this.capacity = capacity;
        this.a = (T[])new Object[capacity];
    }

    public void push(T t) {
        if (pushIndex==capacity && !isEmpty) {
            throw new RuntimeException("Queue is fill ......");
        }
        pushIndex = pushIndex % capacity;
        a[pushIndex++] = t;
        if (pushIndex==takeIndex) {
            isEmpty = false;
        }
    }

    public T pop() {
        if (takeIndex==pushIndex && isEmpty) {
            throw  new RuntimeException("Queue is Empty");
        }
        takeIndex = takeIndex % capacity;
        T t = a[takeIndex++];
        if (pushIndex==takeIndex) {
            isEmpty = true;
        }
        return t;
    }

    public static void main(String[] args) {
        MyCycleQueue<Integer> queue = new MyCycleQueue<>(5);
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 5; i++) {
                queue.push(i);
            }

            for (int i = 0; i < 5; i++) {
                Integer v = queue.pop();
                System.out.print(v+", ");
            }
            System.out.println();
        }

    }
}
