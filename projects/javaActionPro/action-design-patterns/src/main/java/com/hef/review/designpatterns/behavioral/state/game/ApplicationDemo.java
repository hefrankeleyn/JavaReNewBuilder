package com.hef.review.designpatterns.behavioral.state.game;

/**
 * @Date 2022/10/25
 * @Author lifei
 */
public class ApplicationDemo {

    public static void main(String[] args) {
//        MarioStateMachine machine = new MarioStateMachine();
//        MarioStateMachine02 machine = new MarioStateMachine02();
//        MarioStateMachine03 machine = new MarioStateMachine03();
        MarioStateMachine04 machine = new MarioStateMachine04();
        machine.obtainMushRoom();
        int score = machine.getScore();
        MarioState currentState = machine.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + currentState);
    }
}
