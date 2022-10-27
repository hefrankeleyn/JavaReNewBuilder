package com.hef.review.designpatterns.behavioral.state.game.stateandsingleton;

import com.hef.review.designpatterns.behavioral.state.game.MarioState;
import com.hef.review.designpatterns.behavioral.state.game.MarioStateMachine04;

/**
 * @Date 2022/10/27
 * @Author lifei
 */
public class CapeMario implements IMario {

    private final static CapeMario instance = new CapeMario();

    public static CapeMario getInstance() {
        return instance;
    }

    private CapeMario(){}

    @Override
    public MarioState getName() {
        return MarioState.CAPE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine04 marioStateMachine) {
        // 什么也不做
    }

    @Override
    public void obtainCape(MarioStateMachine04 marioStateMachine) {
        // 什么也不做
    }

    @Override
    public void obtainFireFlower(MarioStateMachine04 marioStateMachine) {
        // 什么也不做
    }

    @Override
    public void meetMonster(MarioStateMachine04 marioStateMachine) {
        marioStateMachine.setMarioState(SmallMario.getInstance());
        marioStateMachine.setScore(marioStateMachine.getScore() - 200);
    }
}
