package com.hef.stream.lazy;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Date 2021/9/5
 * @Author lifei
 */
public class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }
    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty()?this:p.test(head())? new LazyList<>(head, ()->tail().filter(p)):tail().filter(p);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
