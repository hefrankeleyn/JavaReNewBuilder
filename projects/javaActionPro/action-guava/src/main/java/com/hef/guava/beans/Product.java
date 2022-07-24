package com.hef.guava.beans;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * @Date 2022/7/17
 * @Author lifei
 */
public class Product implements Comparable<Product>{

    private String name;
    private Integer num;

    private TypeEnum typeEnum;

    @Override
    public int compareTo(Product o) {
        return ComparisonChain.start()
                .compare(this.name, o.name)
                .compare(this.num, o.name)
                .compare(this.typeEnum, o.typeEnum, Ordering.natural())
                .result();
    }

    enum TypeEnum {
        LGE, GLC
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
