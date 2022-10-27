package com.hef.review.designpatterns.behavioral.state.game.stateandsingleton;

import com.hef.review.designpatterns.behavioral.state.game.MarioState;
import com.hef.review.designpatterns.behavioral.state.game.MarioStateMachine04;

public interface IMario {
    MarioState getName();

    // 以下是定义的事件
    void obtainMushRoom(MarioStateMachine04 marioStateMachine);
    void obtainCape(MarioStateMachine04 marioStateMachine);
    void obtainFireFlower(MarioStateMachine04 marioStateMachine);
    void meetMonster(MarioStateMachine04 marioStateMachine);
}
