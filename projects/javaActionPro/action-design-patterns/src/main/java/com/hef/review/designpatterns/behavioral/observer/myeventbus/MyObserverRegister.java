package com.hef.review.designpatterns.behavioral.observer.myeventbus;

import static com.google.common.base.Preconditions.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Observer 注册表
 * @Date 2022/10/23
 * @Author lifei
 */
public class MyObserverRegister {

    // 注册表, 消息类型: 观察者方法
    private ConcurrentMap<Class<?>, CopyOnWriteArraySet<MyObserverAction>> registry = new ConcurrentHashMap<>();

    /**
     * 将观察者注册到 注册表中
     * @param observer 观察者
     */
    public void register(Object observer) {
        Map<Class<?>, Collection<MyObserverAction>> observerActions = findAllObserverActions(observer);
        for (Map.Entry<Class<?>, Collection<MyObserverAction>> entry : observerActions.entrySet()) {
            Class<?> eventType = entry.getKey();
            Collection<MyObserverAction> evenActions = entry.getValue();
            CopyOnWriteArraySet<MyObserverAction> registryEvenActions =
                    registry.getOrDefault(eventType, new CopyOnWriteArraySet<>());
            registryEvenActions.addAll(evenActions);
            registry.put(eventType, registryEvenActions);
        }
    }

    /**
     * 获取匹配的观察者事件
     * @param event
     * @return
     */
    public List<MyObserverAction> getMatchedMyObserverActions(Object event) {
        List<MyObserverAction> result = new ArrayList<>();
        Class<?> postedEventClass = event.getClass();
        for (Map.Entry<Class<?>, CopyOnWriteArraySet<MyObserverAction>> entry : registry.entrySet()) {
            Class<?> eventClass = entry.getKey();
            // 匹配相同类型或父类型
            if (postedEventClass.isAssignableFrom(eventClass)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    // 消息类型（观察者类型类型及其父类型） 观察者方法
    public Map<Class<?>, Collection<MyObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<MyObserverAction>> result = new HashMap<>();
        // 观察者类型
        Class<?> observerClass = observer.getClass();
        for (Method method : getAnnotatedMethods(observerClass)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            result.putIfAbsent(eventType, new ArrayList<>());
            result.get(eventType).add(new MyObserverAction(observer, method));
        }
        return result;
    }

    /**
     * 根据观察者类型，查找方法列表
     * @param clazz
     * @return
     */
    public List<Method> getAnnotatedMethods(Class<?> clazz) {
        List<Method> result = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MySubscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                checkArgument(parameterTypes.length==1,
                        "方法%s 有一个注解@MySubscribe ,它有%s个参数，实际要求有且只有一个参数",
                        method, parameterTypes.length);
                result.add(method);
            }
        }
        return result;
    }

}
