package com.hef.review.designpatterns.behavioral.iterator.snapshot;


import java.util.Iterator;
import java.util.Objects;

/**
 * 包含支持快照的迭代器：
 *  每个元素都有一个时间： addTime 和 delTime
 *     添加一个元素的时候： addTime 为当前时间， delTime 为 Long.MAX_VALUE
 *     删除一个元素的时候： delTime 修改为当前时间
 *     迭代遍历的时候，取： addTime< currentTime < delTime 的元素
 * @Date 2022/10/29
 * @Author lifei
 */
public class ArrayList<E> implements List<E>{

    private static final int DEFAULT_CAPACITY = 10;
    private int actualSize; // 不包含删除标记元素
    private int totalSize; //  包含删除标记元素

    private E[] elements;
    private long[] addTimestamps;
    private long[] delTimestamps;

    public ArrayList() {
        this.elements = (E[])new Object[DEFAULT_CAPACITY];
        this.addTimestamps = new long[DEFAULT_CAPACITY];
        this.delTimestamps = new long[DEFAULT_CAPACITY];
        this.actualSize = 0;
        this.totalSize = 0;
    }

    @Override
    public void add(E obj) {
        elements[totalSize] = obj;
        addTimestamps[totalSize] = System.nanoTime();
        delTimestamps[totalSize] = Long.MAX_VALUE;
        totalSize++;
        actualSize++;
    }

    @Override
    public void remove(E obj) {
        for (int i = 0; i < totalSize; i++) {
            if (Objects.equals(elements[i], obj)) {
                delTimestamps[i] = System.nanoTime();
                actualSize--;
            }
        }
    }

    public int actualSize() {
        return this.actualSize;
    }

    public int totalSize() {
        return this.totalSize;
    }

    public E get(int i) {
        if (i>=totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return elements[i];
    }

    public long getAddTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return addTimestamps[i];
    }

    public long getDelTimestamp(int i) {
        if (i>=totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return delTimestamps[i];
    }

    @Override
    public Iterator<E> iterator() {
        return new SnapshotArrayIterator(this);
    }
}
