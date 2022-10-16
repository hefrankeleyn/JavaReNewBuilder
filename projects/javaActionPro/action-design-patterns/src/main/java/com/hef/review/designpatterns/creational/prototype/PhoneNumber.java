package com.hef.review.designpatterns.creational.prototype;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 原型模式
 * @Date 2022/10/16
 * @Author lifei
 */
public class PhoneNumber implements Cloneable, Serializable {

    private String userName;
    private int[] numbers;

    /**
     * clone方法的通用约定： 这个方法返回的对象应该通过调用 super.clone 获取
     *     如果类的clone方法返回的实例不是通过调用super.clone 方法获得，而是通过调用构造七获得，编译器就不会发出警告，
     *     但是该类的子类调用了super.clone方法，得到的对象就会拥有错误的类，并阻止了clone方法的子类正常工作。
     * 协变返回类型： Object 的clone方法 返回的是 Object，但是这个clone方法返回的却是PhoneNumber。
     *
     * @return
     */
    @Override
    public PhoneNumber clone() {
        try {
            PhoneNumber phoneNumber = (PhoneNumber) super.clone();
            phoneNumber.numbers = numbers.clone();
            return phoneNumber;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(PhoneNumber.class)
                .add("numbers", numbers)
                .add("numbers", userName)
                .toString();
    }
}
