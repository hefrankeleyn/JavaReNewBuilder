package com.hef.domain;

/**
 * @Date 2021/8/22
 * @Author lifei
 */
public class Person {

    public String name = "hef/lf";
    protected Integer age = 1;
    private Byte sex = (byte) 1;

    Boolean isMarriage = true;

    public Person() {

    }

    public Person(String name, Integer age, Byte sex, Boolean isMarriage) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.isMarriage = isMarriage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Boolean getMarriage() {
        return isMarriage;
    }

    public void setMarriage(Boolean marriage) {
        isMarriage = marriage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", isMarriage=" + isMarriage +
                '}';
    }
}
