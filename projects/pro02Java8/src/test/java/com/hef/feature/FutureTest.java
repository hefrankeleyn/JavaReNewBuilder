package com.hef.feature;

import java.util.concurrent.*;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class FutureTest {

    public void executeFutureTest() {
        Future<Double> future = Executors.newCachedThreadPool().submit(() -> Math.random());
        try {
            // 会阻塞
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程在等待过程中被中断
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            // 计算抛出一个异常
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            // 在Future对象完成之前超过已过期
            throw new RuntimeException(e);
        }
    }
}
