package com.hef.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class PrimMain {

    /**
     * 测试两种分类质数的方法， 更科学的测试是使用诸如JMH的框架
     * @param args
     */
    public static void main(String[] args) {
        PrimMain primMain = new PrimMain();
        long fastest = Long.MAX_VALUE;
        for (int i=0; i<10; i++) {
            long start = System.nanoTime();
            // Fastest execution done in 382 ms
            Map<Boolean, List<Integer>> res = primMain.primPartition1(100_0000);
            // Fastest execution done in 255 ms
//            Map<Boolean, List<Integer>> res = primMain.primPartition2(100_0000);
            long during = (System.nanoTime()-start)/1_000_000;
            if (during<fastest) fastest=during;
        }
        System.out.println("Fastest execution done in " + fastest + " ms");
        /*Map<Boolean, List<Integer>> res = primMain.primPartition1(100);
        System.out.println(res.get(true));
        System.out.println(res.get(false));
        System.out.println("===============================");
        Map<Boolean, List<Integer>> res2 = primMain.primPartition2(100);
        System.out.println(res2.get(true));
        System.out.println(res2.get(false));*/
    }

    public Map<Boolean, List<Integer>> primPartition2(int v) {
        return IntStream.rangeClosed(1, v).boxed()
                .collect(new MyPrimCollector());
    }

    /**
     * 对数据进行分组，分区质数和非质数
     * @param v
     * @return
     */
    public Map<Boolean, List<Integer>> primPartition1(int v) {
        return IntStream.rangeClosed(1, v).boxed()
                .collect(Collectors.partitioningBy(PrimMain::isPrim));
    }

    /**
     * 判断是否为质数
     * @param n
     * @return
     */
    public static boolean isPrim(Integer n) {
        int k = (int)Math.sqrt(n);
        return IntStream.rangeClosed(2, k).noneMatch(i->n%i==0);
    }
}
