package com.hef.review.future;

public interface Rates {

    static double getRate() {
        Shop.delay();
        return Math.random();
    }
}
