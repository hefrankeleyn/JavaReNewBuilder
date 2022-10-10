package com.hef.review.designpatterns.creational.builder;

/**
 * 抽象的层级：
 * 子类于子类有共同的属性，也有不一样的属性
 * @Date 2022/10/11
 * @Author lifei
 */
public abstract class AbstractBox {

    protected Integer size;
    protected String color;

    public AbstractBox(Builder builder) {
        this.size = builder.size;
        this.color = builder.color;
    }

    abstract static class Builder<T extends Builder<T>> {
        protected Integer size;
        protected String color;

        // 模拟的self参数：子类必须复写这个方法，返回"this"
        protected abstract T self();

        protected abstract AbstractBox build();

        public T size(Integer size) {
            this.size = size;
            return self();
        }

        public T color(String color) {
            return self();
        }
    }
}
