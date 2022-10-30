package com.hef.review.designpatterns.behavioral.iterator.snapshot;

public interface List<E>  extends Iterable<E> {

    void add(E obj);

    void remove(E obj);
}
