package com.hef.review.designpatterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * 迭代器模式
 * @Date 2022/10/29
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
//        Iterable<MeanItem> dinerMenu = new DinerMenu();
//        Iterable<MeanItem> pancakeHouseMenu = new PancakeHouseMenu();
//        Iterable<MeanItem> cafeMean = new CafeMenu();
//        Waitress waitress = new Waitress(dinerMenu, pancakeHouseMenu, cafeMean);
//        waitress.printMenu();
        ApplicationDemo demo = new ApplicationDemo();
        demo.listDemo();
    }

    private void listDemo() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("aa", "bb", "cc"));
        Iterator<String> iterator = list.iterator();
        System.out.println("第一次遍历：......");
        while (iterator.hasNext()) {
//            iterator.remove(); // 这里调用 remove方法会报错
            System.out.println(iterator.next());
            iterator.remove();
            System.out.println(list);
        }
        Iterator<String> iterator02 = list.iterator();
        System.out.println("第二次遍历：....");
        while (iterator02.hasNext()) {
            System.out.println(iterator02.next());
        }
    }

}
