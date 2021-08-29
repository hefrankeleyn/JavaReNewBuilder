package com.hef.stream;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class MyWordCounter {

    public static void main(String[] args) {
        String str = "i have a dream, good good study, day day up";
        Stream<Character> stream1 = IntStream.range(0, str.length()).mapToObj(str::charAt);
        System.out.println("first version : " + wordCount(stream1));
        Stream<Character> stream2 = IntStream.range(0, str.length()).mapToObj(str::charAt);
        System.out.println("parallel2: " + wordCount(stream2.parallel()));

        Spliterator<Character> spliterator = new MyWordCountSpliterator(str);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("parallel3: " + wordCount(stream));


    }

    private static int wordCount(Stream<Character> stream) {
        MyWordCounter res = stream.reduce(new MyWordCounter(true, 0),
                MyWordCounter::accumulate,
                MyWordCounter::combine);
        return res.counter;
    }


    private final boolean lastSpace;
    private final int counter;


    public MyWordCounter(boolean lastSpace, int counter) {
        this.lastSpace = lastSpace;
        this.counter = counter;
    }

    public MyWordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace?this:new MyWordCounter(true, count());
        }else {
            return lastSpace?new MyWordCounter(false, count()+1):this;
        }
    }

    public MyWordCounter combine(MyWordCounter wordCounter) {
        return new MyWordCounter(wordCounter.lastSpace, this.count()+ wordCounter.count());
    }

    public int count() {
        return counter;
    }

    public int getCounter() {
        return counter;
    }

    public boolean isLastSpace() {
        return lastSpace;
    }
}
