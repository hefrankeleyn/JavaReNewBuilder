package com.hef.future;

/**
 * @Date 2021/8/31
 * @Author lifei
 */
public class Discount {

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
}
