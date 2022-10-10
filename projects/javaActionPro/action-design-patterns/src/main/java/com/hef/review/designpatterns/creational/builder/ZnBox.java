package com.hef.review.designpatterns.creational.builder;

/**
 * 智能盒子
 * @Date 2022/10/11
 * @Author lifei
 */
public class ZnBox extends AbstractBox{

    private Boolean znFlag;
    public ZnBox(Builder builder) {
        super(builder);
        this.znFlag = builder.znFlag;
    }

    public static class Builder extends AbstractBox.Builder<Builder> {

        private Boolean znFlag;

        public Builder znFlag(Boolean znFlag) {
            this.znFlag = znFlag;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        // 协变返回类型：子类声明返回超级类中声明的返回类型的子类
        // 它允许客户端无需类型转换就能使用这些了类型
        @Override
        protected ZnBox build() {
            return new ZnBox(this);
        }
    }
}
