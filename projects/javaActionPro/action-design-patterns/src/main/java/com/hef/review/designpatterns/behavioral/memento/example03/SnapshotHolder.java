package com.hef.review.designpatterns.behavioral.memento.example03;

import java.util.Stack;

/**
 * 快照的持有者
 * @Date 2022/11/2
 * @Author lifei
 */
public class SnapshotHolder {

    private Stack<Snapshot> snapshots = new Stack<>();

    public Snapshot popSnapshot() {
        return snapshots.pop();
    }

    public void pushSnapshot(Snapshot snapshot) {
        this.snapshots.push(snapshot);
    }
}
