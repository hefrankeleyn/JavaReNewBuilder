package com.hef.review.designpatterns.behavioral.state.game.stateandsingleton;

import com.hef.review.designpatterns.behavioral.state.game.MarioState;
import com.hef.review.designpatterns.behavioral.state.game.MarioStateMachine04;

/**
 * @Date 2022/10/27
 * @Author lifei
 */
public class FireFlowerMario implements IMario {

    private static final FireFlowerMario INSTANCE = new FireFlowerMario();

    public static FireFlowerMario getInstance() {
        return INSTANCE;
    }

    private FireFlowerMario(){}
    @Override
    public MarioState getName() {
        return MarioState.FIRE;
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
        marioStateMachine.setScore(marioStateMachine.getScore() - 300);
    }
}
