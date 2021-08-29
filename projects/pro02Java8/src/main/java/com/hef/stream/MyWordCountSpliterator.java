package com.hef.stream;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Date 2021/8/29
 * @Author lifei
 */
public class MyWordCountSpliterator implements Spliterator<Character> {

    private final String str;
    private int currentChar = 0;

    public MyWordCountSpliterator(String str) {
        this.str = str;
    }
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(str.charAt(currentChar++));
        return currentChar<str.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = str.length() - currentChar;
        // 要解析的字符串足够小，就直接顺序处理
        if (currentSize<10) {
            return null;
        }
        for (int pos=currentChar+currentSize/2;pos<str.length(); pos++) {
            if (Character.isWhitespace(str.charAt(pos))) {
                MyWordCountSpliterator spliterator = new MyWordCountSpliterator(str.substring(currentChar, pos));
                currentChar = pos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return str.length()-currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
