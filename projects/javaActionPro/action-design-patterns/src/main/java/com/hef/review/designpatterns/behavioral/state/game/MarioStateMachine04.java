package com.hef.review.designpatterns.behavioral.state.game;

import com.hef.review.designpatterns.behavioral.state.game.stateandsingleton.IMario;
import com.hef.review.designpatterns.behavioral.state.game.stateandsingleton.SmallMario;

/**
 * 状态机结合单例模式
 * @Date 2022/10/27
 * @Author lifei
 */
public class MarioStateMachine04 {

    private IMario marioState;
    private int score;

    public MarioStateMachine04() {
        this.score = 0;
        this.marioState = SmallMario.getInstance();
    }

    public MarioState getCurrentState() {
        return marioState.getName();
    }

    public IMario getMarioState() {
        return marioState;
    }

    public void setMarioState(IMario marioState) {
        this.marioState = marioState;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void obtainMushRoom() {
        marioState.obtainMushRoom(this);
    }

    public void obtainCape() {
        marioState.obtainCape(this);
    }

    public void obtainFireFlower() {
        marioState.obtainFireFlower(this);
    }
    public void meetMonster() {
        marioState.meetMonster(this);
    }
}
