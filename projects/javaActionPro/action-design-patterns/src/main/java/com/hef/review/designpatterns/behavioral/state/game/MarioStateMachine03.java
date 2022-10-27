package com.hef.review.designpatterns.behavioral.state.game;

import com.google.common.base.MoreObjects;
import com.hef.review.designpatterns.behavioral.state.game.state.*;

/**
 * 状态模式的状态机
 * @Date 2022/10/27
 * @Author lifei
 */
public class MarioStateMachine03 {

    private IMario smallState;
    private IMario superState;
    private IMario capeState;
    private IMario fireFlowerState;

    private IMario marioState;
    private int score;

    public MarioStateMachine03() {
        this.smallState = new SmallMario(this);
        this.superState = new SuperMario(this);
        this.capeState  = new CapeMario(this);
        this.fireFlowerState = new FireFlowerMario(this);
        this.marioState = smallState;
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

    public IMario getSmallState() {
        return smallState;
    }

    public IMario getSuperState() {
        return superState;
    }

    public IMario getCapeState() {
        return capeState;
    }

    public IMario getFireFlowerState() {
        return fireFlowerState;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void obtainMushRoom() {
        marioState.obtainMushRoom();
    }

    public void obtainCape() {
        marioState.obtainCape();
    }

    public void obtainFireFlower() {
        marioState.obtainFireFlower();
    }
    public void meetMonster() {
        marioState.meetMonster();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(MarioStateMachine03.class)
                .add("marioState", marioState)
                .add("score", score)
                .toString();
    }
}
