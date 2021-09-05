package com.hef.stream;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Date 2021/9/5
 * @Author lifei
 */
public class ConverterTest {

    public static void main(String[] args) {
        IntStream primes = primes(10);
        String res = primes.boxed().map(v -> v + "").collect(Collectors.joining(", "));
        System.out.println(res);
    }

    /**
     * 获取数字流
     * @return
     */
    public static IntStream numbers() {
        return IntStream.iterate(2, i->i+1);
    }

    /**
     * 获取第一个值
     * @param stream
     * @return
     */
    private static int head(IntStream stream) {
        return stream.findFirst().getAsInt();
    }

    /**
     * 获取尾部元素
     * @param stream
     * @return
     */
    private static IntStream tail(IntStream stream) {
        return stream.skip(1);
    }

    private static IntStream primeStream(IntStream numbers) {
        int head = head(numbers);
        return numbers;
    }

    /**
     * 获取n个质数
     * @param n
     * @return
     */
    private static IntStream primes(int n) {
        return IntStream.iterate(2, i -> i + 1)
                .filter(ConverterTest::isPrime)
                .limit(n);
    }

    /**
     * 判断是否为质数
     * @param v
     * @return
     */
    private static boolean isPrime(int v) {
        int vRoot = (int)Math.sqrt(v);
        return IntStream.rangeClosed(2, vRoot).noneMatch(i->v%i==0);
    }
}
