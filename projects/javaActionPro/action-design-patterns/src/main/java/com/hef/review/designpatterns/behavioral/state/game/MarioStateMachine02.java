package com.hef.review.designpatterns.behavioral.state.game;

import static com.hef.review.designpatterns.behavioral.state.game.MarioState.*;
import static com.hef.review.designpatterns.behavioral.state.game.MarioEvent.*;


/**
 * 马里奥 状态机: 查表法
 * @Date 2022/10/25
 * @Author lifei
 */
public class MarioStateMachine02 {

    private int score;
    private MarioState currentState;

    // 行号是 MarioState.value, 列号是 MarioEvent.value
    private static final MarioState[][] transitionTable = {
            // SMALL: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {SUPER, CAPE, FIRE, SMALL},
            // SUPER: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {SUPER, CAPE, FIRE, SMALL},
            // FIRE: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {FIRE, FIRE, FIRE, SMALL},
            // CAPE: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {CAPE, CAPE, CAPE, SMALL},
    };

    private static final int[][] actionTable = {
            // SMALL: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {+100, +200, +300, +0},
            // SUPER: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {+0, +200, +300, -100},
            // FIRE: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {+0, +0, +0, -300},
            // CAPE: GOT_MUSHROOM ,GOT_CAPE ,GOT_FIRE ,MET_MONSTER
            {+0, +0, +0, -200},
    };

    public MarioStateMachine02() {
        this.score = 0;
        this.currentState = SMALL;
    }

    /**
     * 获得蘑菇，变成超级 super, 并加100积分
     */
    public void obtainMushRoom() {
        executeEvent(GOT_MUSHROOM);
    }

    /**
     * 获得斗篷，加200积分，变身 斗篷马里奥
     */
    public void obtainCape() {
        executeEvent(GOT_CAPE);
    }

    /**
     * 获得火焰，+300分， 变成火焰马里奥
     */
    public void obtainFireFlower() {
        executeEvent(GOT_FIRE);
    }

    /**
     * 遇到怪兽
     */
    public void meetMonster() {
        executeEvent(MET_MONSTER);
    }

    private void executeEvent(MarioEvent event) {
        int stateRow = currentState.getValue();
        int eventRow = event.getValue();
        this.currentState = transitionTable[stateRow][eventRow];
        this.score = actionTable[stateRow][eventRow];
    }

    public int getScore() {
        return score;
    }

    public MarioState getCurrentState() {
        return currentState;
    }
}
