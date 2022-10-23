package com.hef.review.designpatterns.behavioral.observer.myeventbus;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个注解，表明观察者中的哪个函数可以接收消息
 */
@Retention(RetentionPolicy.RUNTIME)  // 注解的声明周期
@Target(ElementType.METHOD)  // 注解作用的地方
@Beta  // 标注API在未来发行的版本是可能有不兼容的变化
public @interface MySubscribe {
}
