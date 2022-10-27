package com.hef.review.designpatterns.behavioral.state.game.stateandsingleton;

import com.hef.review.designpatterns.behavioral.state.game.MarioState;
import com.hef.review.designpatterns.behavioral.state.game.MarioStateMachine04;

/**
 * @Date 2022/10/27
 * @Author lifei
 */
public class SuperMario implements IMario {

    private static final SuperMario instance = new SuperMario();

    private SuperMario(){}

    public static SuperMario getInstance() {
        return instance;
    }

    @Override
    public MarioState getName() {
        return MarioState.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine04 marioStateMachine) {
        // 什么也不做
    }

    @Override
    public void obtainCape(MarioStateMachine04 marioStateMachine) {
        marioStateMachine.setMarioState(CapeMario.getInstance());
        marioStateMachine.setScore(marioStateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine04 marioStateMachine) {
        marioStateMachine.setMarioState(FireFlowerMario.getInstance());
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine04 marioStateMachine) {
        marioStateMachine.setMarioState(SmallMario.getInstance());
        marioStateMachine.setScore(marioStateMachine.getScore() - 100);
    }
}
