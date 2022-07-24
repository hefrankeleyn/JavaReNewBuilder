package com.hef.stream;

/**
 * @Date 2022/7/3
 * @Author lifei
 */
public class WorldCount {

    private final int counter;
    private final boolean lastSpace;

    public WorldCount(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WorldCount accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace?this:new WorldCount(counter, true);
        }else {
            return lastSpace?new WorldCount(counter+1, false):this;
        }
    }

    public WorldCount combine(WorldCount worldCount) {
        return new WorldCount(counter+ worldCount.counter, worldCount.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
