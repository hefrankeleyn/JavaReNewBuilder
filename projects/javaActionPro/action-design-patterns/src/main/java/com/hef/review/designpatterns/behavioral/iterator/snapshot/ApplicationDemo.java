package com.hef.review.designpatterns.behavioral.iterator.snapshot;

import java.util.Iterator;

/**
 * 测试支持快照的迭代器
 * @Date 2022/10/30
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator1 = list.iterator();
        list.remove(2);
        Iterator<Integer> iterator2 = list.iterator();

        showIterator(iterator1);
        showIterator(iterator2);
    }

    private static void showIterator(Iterator<Integer> iterator) {
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();
    }
}
