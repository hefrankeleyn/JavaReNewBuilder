package com.hef.guava.future;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date 2022/7/31
 * @Author lifei
 */
public class ListenableFutureDemo {

    public static void main(String[] args) {
//        createFutureDemo01();
//        ListenableFutureTask<String> futureTask = ListenableFutureTask.create(() -> callDemo());
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListeningExecutorService otherExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<ListenableFuture<String>> nestedFuture =
                executorService.submit(() -> otherExecutorService.submit(() -> callDemo()));
    }

    private static void createFutureDemo01() {
        ListeningExecutorService service =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        ListenableFuture<String> listenableFuture = service.submit(() -> callDemo());
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Run Ok : " + result);
//                service.shutdown();
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Run Fail: " + t);
            }
        }, service);
//        service.shutdown();

//        Futures.successfulAsList()
    }

    private static String asyncFuncDemo(String s) {
        return s+"01";
    }

    private static String callDemo() {
        try {
            Thread.sleep(3000);
            return "hello world";
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
