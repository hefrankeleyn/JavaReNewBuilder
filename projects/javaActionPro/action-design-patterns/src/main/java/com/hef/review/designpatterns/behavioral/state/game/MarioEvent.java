package com.hef.review.designpatterns.behavioral.state.game;

/**
 * 定义行为
 * @Date 2022/10/26
 * @Author lifei
 */
public enum MarioEvent {

    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3),
    ;

    private int value;

    MarioEvent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
