package com.hef.review.designpatterns.behavioral.iterator.snapshot;

import java.util.Iterator;

/**
 * @Date 2022/10/30
 * @Author lifei
 */
public class SnapshotArrayIterator<E> implements Iterator<E> {

    private long snapshotTimestamp;
    private int cursorInAll; // 在整个容器的下标，而非快照的下标
    private int leftCount; // 快照中还有几个元素未被遍历
    private ArrayList<E> list;

    public SnapshotArrayIterator(ArrayList<E> list) {
        this.snapshotTimestamp = System.nanoTime();
        this.list = list;
        this.leftCount = list.actualSize();
        this.cursorInAll = 0;
        justNext();
    }
    @Override
    public boolean hasNext() {
        return this.leftCount>0;
    }

    @Override
    public E next() {
        E currentItem = list.get(cursorInAll++);
        justNext();
        leftCount--;
        return currentItem;
    }

    private void justNext() {
        if (leftCount>0) {
            while (cursorInAll<list.totalSize()) {
                if (snapshotTimestamp>list.getAddTimestamp(cursorInAll)
                        && snapshotTimestamp< list.getDelTimestamp(cursorInAll)) {
                    break;
                }
                cursorInAll++;
            }
        }
    }
}
