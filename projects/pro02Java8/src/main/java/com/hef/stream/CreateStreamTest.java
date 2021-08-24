package com.hef.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Date 2021/8/24
 * @Author lifei
 */
public class CreateStreamTest {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(2, 3, 4, 5, 6);

        Stream<Integer> stream1 = Arrays.stream(new Integer[]{3, 5, 6, 7});

        // 由文件生成流
        String filePath = "/Users/lifei/Documents/workspace/githubRepositoies/JavaNoneRebuild/projects/pro02Java8/pom.xml";
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath), Charset.defaultCharset())){
            long count = fileStream.flatMap(line -> Arrays.stream(line.split(" "))).count();
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 通过函数创建流
        Stream.iterate(0, n->n+2).limit(10).forEach(k-> System.out.print(k+ " "));
        System.out.println();
        // 斐波那契数列
        System.out.println("iterate 产生斐波那契 序列");
        Stream.iterate(new Integer[]{0, 1}, a->new Integer[]{a[1], a[0]+a[1]})
                .limit(10).map(a->a[0]).forEach(k-> System.out.print(k+ " "));
        System.out.println();

        Stream.generate(Math::random).limit(5).forEach(System.out::println);

        // 使用generate 产生斐波拉契序列
        System.out.println("generate 产生斐波那契 序列");
        IntStream.generate(new IntSupplier() {
            private int pre = 0;
            private int cur = 1;
            @Override
            public int getAsInt() {
                int oldPre = this.pre;
                int nexVal = this.pre + this.cur;
                this.pre = this.cur;
                this.cur = nexVal;
                return oldPre;
            }
        }).limit(10).forEach(k-> System.out.print(k+ " "));
    }
}
