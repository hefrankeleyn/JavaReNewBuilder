package com.hef.stream.lazy;

import java.util.function.Predicate;

/**
 * @Date 2021/9/5
 * @Author lifei
 */
public interface MyList<T> {

    T head();
    MyList<T> tail();

    MyList<T> filter(Predicate<T> p);

    default boolean isEmpty() {
        return true;
    }
}
