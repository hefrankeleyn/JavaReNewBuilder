package com.hef.review.designpatterns.behavioral.state.game;

/**
 * 马里奥的状态
 * @Date 2022/10/25
 * @Author lifei
 */
public enum MarioState {

    SMALL(0), // 小马里奥
    SUPER(1), // 超级马里奥
    FIRE(2), // 火焰马里奥
    CAPE(3) // 斗篷马里奥
    ;
    private int value;

    MarioState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
