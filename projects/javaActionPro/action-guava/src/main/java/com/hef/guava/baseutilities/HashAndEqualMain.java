package com.hef.guava.baseutilities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.hef.guava.beans.Product;

import java.util.Comparator;

/**
 * @Date 2022/7/17
 * @Author lifei
 */
public class HashAndEqualMain {

    public static void main(String[] args) {
//        equalTest();
        HashAndEqualMain demo = new HashAndEqualMain();
        String res = demo.toString();
        System.out.println(res);

    }

    private static void equalTest() {
        boolean r1 = Objects.equal(null, "aa");
        boolean r2 = Objects.equal("cc", null);
        boolean r3 = Objects.equal(null, null);
        System.out.println(r3);
        int num = Objects.hashCode("aa", "cc", "dd");
        System.out.println(num);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("MyObject")
                .add("x", 1)
                .add("y", "cc")
                .toString();
    }
}
