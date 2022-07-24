package com.hef.guava.baseutilities;

import com.google.common.base.Throwables;

import java.io.IOException;

/**
 * @Date 2022/7/17
 * @Author lifei
 */
public class ThrowDemo {

    public static void main(String[] args) {
        ThrowDemo throwDemo = new ThrowDemo();
        try {
            Throwable throwable = new IllegalArgumentException(new ArrayStoreException());
            Throwable rootCause = Throwables.getRootCause(throwable);
            System.out.println(rootCause);
            System.out.println(Throwables.getCausalChain(throwable));
            System.out.println(Throwables.getStackTraceAsString(throwable));
            throwDemo.test01("aa");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void test01(String name) throws IOException,IllegalArgumentException {
        try {
            throw new IllegalArgumentException();
        }catch (Throwable t) {
//            Throwables.propagateIfPossible(t, IOException.class);
            Throwables.throwIfInstanceOf(t, IllegalArgumentException.class);
            throw new RuntimeException(t);
        }
    }
}
