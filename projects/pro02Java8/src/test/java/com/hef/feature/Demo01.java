package com.hef.feature;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Date 2022/6/27
 * @Author lifei
 */
public class Demo01 {

    @Test
    public void findHiddenFiles() {
        //  查询所有隐藏的文件
        // 第一种写法
        File[] files01 = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        // 第二种写法
        File[] files02 = new File(".").listFiles(File::isHidden);

        List<Integer> numbers = new ArrayList<>();
        numbers.stream().reduce(0, Integer::sum);


        List<Apple> list01 = new ArrayList<>();
        list01.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));
        Predicate<Apple> weightApple = (apple)->apple.getWeight()>150;
        Predicate<Apple> beijingApple = (apple)->apple.getCountry().equals("beijing");
        Predicate<Apple> negate = weightApple.negate();
        Predicate<Apple> and = weightApple.and(beijingApple);
        Predicate<Apple> or = weightApple.or(beijingApple);
        Function<Integer, Integer> f = x->x+1;
        Function<Integer, Integer> g = x->x*2;
        Function<Integer, Integer> andThen = f.andThen(g);
        Function<Integer, Integer> compose = f.compose(g);
        System.out.println(andThen.apply(3));
        System.out.println(compose.apply(3));
        List<String> words = new ArrayList<>(Arrays.asList("Hello", "World"));
        List<String> charList = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).collect(Collectors.toList());


    }
}
