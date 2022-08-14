package com.hef.guava.reflection;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.*;

import javax.annotation.Nullable;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

/**
 * @Date 2022/8/7
 * @Author lifei
 */
public class ReflectionDemo {

    public static void main(String[] args) {
//        generatorTest();
//        obtainTypeToken();

//        incorrectMapToken();

//        queriesMethods();

//        resolveTypeTest();

//        invokableMethodTest();

//        reflectionTest();

        try {
            ClassPath classPath = ClassPath.from(ReflectionDemo.class.getClassLoader());
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses("com.hef.guava.reflection");
            for (ClassPath.ClassInfo topLevelClass : topLevelClasses) {
                System.out.println(topLevelClass);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void reflectionTest() {
        Foo foo = Reflection.newProxy(Foo.class, new FooInvocationHandler());

        Foo foo1 = (Foo)Proxy.newProxyInstance(Foo.class.getClassLoader(),
                new Class[]{Foo.class},
                new FooInvocationHandler());
    }

    private static void invokableMethodTest() {
        TypeToken<List<String>> stringToken = new TypeToken<List<String>>() {};

        try {
//            Method method = List.class.getMethod("size");
            Method method = List.class.getMethod("add", Object.class);
            Invokable<?, Object> invokable = Invokable.from(method);
            boolean aPublic = invokable.isPublic();
            System.out.println(aPublic);
            System.out.println(invokable.isOverridable());
            boolean aPublic1 = Modifier.isPublic(method.getModifiers());
            System.out.println(aPublic1);

            boolean f = !(Modifier.isFinal(method.getModifiers())
                    || Modifier.isPrivate(method.getModifiers())
                    || Modifier.isStatic(method.getModifiers())
                    || Modifier.isFinal(method.getModifiers()));

            boolean hasNullable = false;
            for (Annotation annotation : method.getParameterAnnotations()[0]) {
                if (annotation instanceof Nullable) {
                    hasNullable = true;
                    break;
                }
            }
            System.out.println(hasNullable);

            boolean annotationPresent = invokable.getParameters().get(0).isAnnotationPresent(Nullable.class);
            System.out.println(annotationPresent);

            TypeToken<List<String>> stringListToken = new TypeToken<List<String>>() {};
            Invokable<List<String>, Object> invokable1 = stringListToken.method(List.class.getMethod("size"));
            TypeToken<?> returnType = invokable1.getReturnType();
            System.out.println(returnType);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void resolveTypeTest() {
        TypeToken<Function<String, Integer>> functionTypeToken = new TypeToken<Function<String, Integer>>() {};
        TypeToken<?> typeToken = functionTypeToken.resolveType(Function.class.getTypeParameters()[0]);
        // java.lang.String
        System.out.println(typeToken);

        try {
            TypeToken<Map<String, Integer>> mapTypeToken = new TypeToken<Map<String, Integer>>() {};
            TypeToken<?> entrySet =
                    mapTypeToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
            System.out.println(entrySet);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void queriesMethods() {
        TypeToken<List<String>> stringListToken = new TypeToken<List<String>>(){};
        Type type = stringListToken.getType();
        System.out.println(type);
        Class<? super List<String>> rawType = stringListToken.getRawType();
        System.out.println(rawType);
        TypeToken<? extends List<String>> subtype = stringListToken.getSubtype(ArrayList.class);
        System.out.println(subtype);
        TypeToken<? super List<String>> supertype = stringListToken.getSupertype(List.class);
        System.out.println(supertype);
        boolean supertypeOf = stringListToken.isSupertypeOf(supertype);
        System.out.println(supertypeOf);
        TypeToken<List<String>>.TypeSet types = stringListToken.getTypes();
        System.out.println(types);
        System.out.println(types.interfaces());
        System.out.println(types.classes());
        boolean array = stringListToken.isArray();
        System.out.println(array);
        TypeToken<?> componentType = stringListToken.getComponentType();
        System.out.println(componentType);
    }

    private static void incorrectMapToken() {
        // 打印：java.util.Map<K, V>
        System.out.println(Util.<String, BigInteger>incorrectMapToken());
        // 打印： java.util.Map<java.lang.String, java.math.BigInteger>
        System.out.println(mapToToken(TypeToken.of(String.class), TypeToken.of(BigInteger.class)));
        TypeToken<String> type = new IKnowMyType<String>(){}.type;
        System.out.println(type);
    }

    private static void generatorTest() {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
    }


    private static void obtainTypeToken() {
        TypeToken<String> stringToken = TypeToken.of(String.class);
        TypeToken<Integer> intToken = TypeToken.of(Integer.class);
        TypeToken<List<String>> stringListToken = new TypeToken<List<String>>() {};
        TypeToken<Map<?,?>> mapToken = new TypeToken<Map<?, ?>>() {};
        TypeToken<Map<String, Integer>> mapToken02 = new TypeToken<Map<String, Integer>>() {}
                .where(new TypeParameter<String>() {}, TypeToken.of(String.class))
                .where(new TypeParameter<Integer>() {}, TypeToken.of(Integer.class));

        TypeToken<Map<String, Integer>> mapTypeToken03 =
                mapToToken(TypeToken.of(String.class), TypeToken.of(Integer.class));
        TypeToken<Map<Integer, BigInteger>> mapTypeToken04 =
                mapToToken(TypeToken.of(Integer.class), TypeToken.of(BigInteger.class));
        TypeToken<Map<Integer, Queue<String>>> mapTypeToken05 =
                mapToToken(TypeToken.of(Integer.class), new TypeToken<Queue<String>>() {});
    }

    private static <K,V> TypeToken<Map<K, V>> mapToToken(TypeToken<K> kTypeToken, TypeToken<V> vTypeToken) {
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, kTypeToken)
                .where(new TypeParameter<V>() {}, vTypeToken);
    }
}

class Util {
    static <K,V> TypeToken<Map<K, V>> incorrectMapToken() {
        return new TypeToken<Map<K, V>>() {};
    }
}


abstract class IKnowMyType<T> {
    TypeToken<T> type = new TypeToken<T>(getClass()){};
}