package com.hef.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Date 2021/8/31
 * @Author lifei
 */
public class FutureDemo {

    private static Random random = new Random(23);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return createValue();
            }
        });
        doSomthing();
        try {
            // 阻塞，直至得到返回结果
            Double v = future.get();
            System.out.println(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void doSomthing() {
        System.out.println("do something other...");
    }

    private static double createValue() {
        dely();
        return random.nextDouble();
    }


    public static void dely() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
