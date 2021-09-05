package com.hef.stream.lazy;

/**
 * @Date 2021/9/5
 * @Author lifei
 */
public class LazyListMain {

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        Integer res2 = numbers.head();
        Integer res3 = numbers.tail().head();
        Integer res4 = numbers.tail().tail().head();
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        System.out.println("创建一个无限延迟的质数列表");
        MyList<Integer> primes = primes(numbers);
        for (int i=0; i<30; i++) {
            if (!primes.isEmpty()){
                System.out.print(primes.head() + ", ");
                primes = primes.tail();
            }
        }
        System.out.println();

    }

    /**
     * 创建一个无限延迟的列表
     * @param n
     * @return
     */
    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, ()->from(n+1));
    }

    /**
     * 创建一个无限循环的 质数列表
     * @param numbers
     * @return
     */
    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), ()->primes(numbers.tail().filter(n->n%numbers.head()!=0)));
    }

}
