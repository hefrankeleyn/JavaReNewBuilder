package com.hef.review.designpatterns.behavioral.state.game.state;

import com.hef.review.designpatterns.behavioral.state.game.MarioState;
import com.hef.review.designpatterns.behavioral.state.game.MarioStateMachine03;

/**
 * @Date 2022/10/27
 * @Author lifei
 */
public class SuperMario implements IMario{

    private MarioStateMachine03 marioStateMachine;

    public SuperMario(MarioStateMachine03 marioStateMachine) {
        this.marioStateMachine = marioStateMachine;
    }

    @Override
    public MarioState getName() {
        return MarioState.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        // 什么也不做
    }

    @Override
    public void obtainCape() {
        marioStateMachine.setMarioState(marioStateMachine.getCapeState());
        marioStateMachine.setScore(marioStateMachine.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
        marioStateMachine.setMarioState(marioStateMachine.getFireFlowerState());
        marioStateMachine.setScore(marioStateMachine.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        marioStateMachine.setMarioState(marioStateMachine.getSmallState());
        marioStateMachine.setScore(marioStateMachine.getScore() - 100);
    }
}
