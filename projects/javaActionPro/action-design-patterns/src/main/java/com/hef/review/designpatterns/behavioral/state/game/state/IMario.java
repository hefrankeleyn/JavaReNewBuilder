package com.hef.review.designpatterns.behavioral.state.game.state;

import com.hef.review.designpatterns.behavioral.state.game.MarioState;

/**
 * 所有状态类的接口
 * @Date 2022/10/27
 * @Author lifei
 */
public interface IMario {
    // 当前状态的名称
    MarioState getName();
    // 以下是定义的事件
    void obtainMushRoom();
    void obtainCape();
    void obtainFireFlower();
    void meetMonster();
}
