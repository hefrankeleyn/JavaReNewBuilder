package com.hef.review.designpatterns.behavioral.state.game;

import java.util.Objects;

import static com.hef.review.designpatterns.behavioral.state.game.MarioState.*;
/**
 * 马里奥 状态机: 分支逻辑法
 * @Date 2022/10/25
 * @Author lifei
 */
public class MarioStateMachine {

    private int score;
    private MarioState currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SMALL;
    }

    /**
     * 获得蘑菇，变成超级 super, 并加100积分
     */
    public void obtainMushRoom() {
        if (Objects.equals(currentState,SMALL)) {
            this.score += 100;
            this.currentState = SUPER;
        }
    }

    /**
     * 获得斗篷，加200积分，变身 斗篷马里奥
     */
    public void obtainCape() {
        if (Objects.equals(currentState,SMALL) || Objects.equals(currentState , SUPER)) {
            this.currentState = CAPE;
            this.score += 200;
        }
    }

    /**
     * 获得火焰，+300分， 变成火焰马里奥
     */
    public void obtainFireFlower() {
        if (Objects.equals(currentState,SMALL) || Objects.equals(currentState,SUPER)) {
            this.currentState = FIRE;
            this.score += 300;
        }
    }

    /**
     * 遇到怪兽
     */
    public void meetMonster() {
        if (Objects.equals(currentState,CAPE)) {
            this.score -= 200;
            this.currentState = SMALL;
        } else if (Objects.equals(currentState,SUPER)) {
            this.score -= 100;
            this.currentState = SMALL;
        } else if (Objects.equals(currentState , FIRE)) {
            this.score -= 300;
            this.currentState = SMALL;
        }
    }

    public int getScore() {
        return score;
    }

    public MarioState getCurrentState() {
        return currentState;
    }
}
