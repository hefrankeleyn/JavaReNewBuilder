package com.hef.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Date 2021/9/5
 * @Author lifei
 */
public class SubListTest {

    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>(Arrays.asList(1,4,9));
        List<List<Integer>> res = findAllSubList(list);
        System.out.println(res);*/

        long start = System.nanoTime();
        // res : 3628800, 0 ms
//        int res = factorialIterator(10);
        int res = factorialRecursive(10);
        int res1 = factorialStream(10);
        int res2 = factorialTailIterator(10);
        System.out.println(res2);
        System.out.println(res1);
        long during = (System.nanoTime() - start)/1_000_000;
        System.out.println("res : " + res + ", "+during + " ms");
    }

    /**
     * 尾-递 迭代
     * @param n
     * @return
     */
    public static int factorialTailIterator(int n) {
        return factorialTailHelp(1, n);
    }

    /**
     * 尾-递 迭代递帮助类
     * @param acc
     * @param n
     * @return
     */
    private static int factorialTailHelp(int acc, int n) {
        return n==1?acc:factorialTailHelp(acc*n, n-1);
    }


    /**
     * 使用迭代计算阶乘
     * r 和 i 在每轮迭代中都会更新
     * @param n
     * @return
     */
    public static int factorialIterator(int n) {
        int r = 1;
        for (int i=1; i<=n; i++) {
            r *= i;
        }
        return r;
    }

    /**
     * 使用递归 计算阶乘
     *  比迭代都效率差：因为每次递归都需要创建栈桢
     *  容易抛出： StackOverflowError异常
     * @param n
     * @return
     */
    public static int factorialRecursive(int n) {
        return n==1? 1 : n * factorialRecursive(n-1);
    }

    /**
     * 使用Stream 计算阶乘
     * @param n
     * @return
     */
    public static int factorialStream(int n) {
        return IntStream.rangeClosed(1, n).reduce(1, (x, y)->x*y);
    }

    /**
     * 获取元素的子集
     * @param list
     * @return
     */
    public static List<List<Integer>> findAllSubList(List<Integer> list) {
        if (list.size()==0) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Collections.emptyList());
            return res;
        }
        Integer first = list.get(0);
        List<Integer> subList = list.subList(1, list.size());
        List<List<Integer>> allSubList = findAllSubList(subList);
        List<List<Integer>> allSubList2 = insertAll(first, allSubList);
        return concat(allSubList, allSubList2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> allSubList, List<List<Integer>> allSubList2) {
        List<List<Integer>> res = new ArrayList<>();
        res.addAll(allSubList);
        res.addAll(allSubList2);
        return res;
    }

    private static List<List<Integer>> insertAll(Integer item, List<List<Integer>> allSubList) {
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> a : allSubList) {
            List<Integer> oneList = new ArrayList<>();
            oneList.addAll(a);
            oneList.add(item);
            res.add(oneList);
        }
        return res;
    }
}
