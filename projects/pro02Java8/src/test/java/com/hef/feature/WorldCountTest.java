package com.hef.feature;

import com.hef.stream.HomeA;
import com.hef.stream.StudentB;
import com.hef.stream.WordCounterSpliterator;
import com.hef.stream.WorldCount;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class WorldCountTest {

    @Test
    public void countWordsTest() {
        final String s = " Nel mezzo del cammin di nostra vita "
                + "mi ritrovai in una   selva oscura"
                + " ché la dritta via era smarrita ";
        System.out.println("Find " + countWordsIteratively(s) + " words");
//        Stream<Character> characterStream = IntStream.range(0, s.length()).mapToObj(s::charAt);
        System.out.println("Find " + countWords(IntStream.range(0, s.length()).mapToObj(s::charAt)) + " words");
        // 由于，默认并发流会随意的拆分，导致并发时出错，只有自定义拆分策略才能解决这个问题
        System.out.println("Find " + countWords(IntStream.range(0, s.length()).mapToObj(s::charAt).parallel()) + " words");
        // 使用自定义的Spliterator
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        // 第二个参数表示要创建一个并行流
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Find " + countWords(StreamSupport.stream(spliterator, true)) + " words");

//        List<String> worlds = new ArrayList<>(Arrays.asList(s.split(" ")));
//        Integer result2 = worlds.stream().reduce(0, (Integer a, String b) -> a + b.length(),
//                (Integer a2, Integer b2) -> {
//                    System.out.println("parallel...");
//            return a2 + b2;
//                });
//        System.out.println(result2);
//        int parallel = IntStream.rangeClosed(0, 5).parallel().reduce(0, (a, b) -> {
//            System.out.println("Parallel");
//            return a + b;
//        });
//        System.out.println(parallel);

    }

    public int countWords(Stream<Character> stream) {
        // 第三个参数，是并行时调用的
        WorldCount result = stream.reduce(new WorldCount(0, true), WorldCount::accumulate, WorldCount::combine);
        return result.getCounter();
    }

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            }else {
                if (lastSpace) counter++;
                lastSpace=false;
            }
        }
        return counter;
    }


    @Test
    public void optionalTest() {
        Optional<Apple> apple = Optional.ofNullable(null);
        Optional<Integer> result = apple.map(Apple::getWeight);
        System.out.println(result.orElse(-1));
        Optional<String> s = Optional.ofNullable(null);
        s.filter(i->i.equals("aa")).ifPresent(System.out::println);
    }

    @Test
    public void optionalTest02() {
        Optional<HomeA> homeA = Optional.ofNullable(null);
        String res = homeA.flatMap(HomeA::getStudentB)
                .map(StudentB::getName)
                .orElse("aa");
        System.out.println(res);
    }
}
