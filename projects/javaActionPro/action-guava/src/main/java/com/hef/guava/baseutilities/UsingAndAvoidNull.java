package com.hef.guava.baseutilities;


import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.*;
/**
 * @Date 2022/7/10
 * @Author lifei
 */
public class UsingAndAvoidNull {

    public static void main(String[] args) {
        UsingAndAvoidNull item = new UsingAndAvoidNull();
//        item.optionalTest();
//        item.stringNullTest();
        item.preconditionTest(3, 5);
    }

    public void optionalTest() {
//        Optional<Object> of1 = Optional.of(null);
        Optional<Integer> optional = Optional.of(5);
        Optional<Object> absent = Optional.absent();
        Optional<Object> objectOptional = Optional.fromNullable(null);
//        Object res = absent.get();
//        System.out.println(res);
//        optional.isPresent()
//        Set<Integer> set = optional.asSet();
//        System.out.println(set);
        System.out.println(objectOptional.isPresent());
        Integer first = MoreObjects.firstNonNull(null, null);
        System.out.println("first: "+ first);

        Set<Object> result = objectOptional.asSet();
        System.out.println(result);
    }

    public void stringNullTest() {
        String val01 = Strings.nullToEmpty(null);
        System.out.println(val01);
        boolean val02 = Strings.isNullOrEmpty(null);
        System.out.println(val02);
        String val03 = Strings.emptyToNull("");
        System.out.println(val03);
    }

    public void preconditionTest(int i, int j) {
        checkArgument(i>0, "Argument was %s but expected nonnegative", i);
//        checkArgument(i>j, "Expected i > j, but %s >= %s", i, j);
        Integer values = checkNotNull(5);
        System.out.println(values);
        int index = checkElementIndex(0, 3);
        System.out.println(index);
        checkPositionIndexes(0, 3, 1);


    }
}
