package com.hef.domain;

/**
 * @Date 2021/8/22
 * @Author lifei
 */
public class Student extends Person{

    private String snum;

    private void study() {
        System.out.println("I learn English.");
    }

    public Student() {
        super();
    }

    public String getSnum() {
        return snum;
    }

    public void setSnum(String snum) {
        this.snum = snum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMarriage=" + isMarriage +
                ", snum='" + snum + '\'' +
                '}';
    }
}
