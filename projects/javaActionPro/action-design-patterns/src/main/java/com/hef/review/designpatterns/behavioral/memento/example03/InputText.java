package com.hef.review.designpatterns.behavioral.memento.example03;

/**
 * 输入的内容
 * @Date 2022/11/2
 * @Author lifei
 */
public class InputText {

    private StringBuilder text = new StringBuilder();

    public void append(String input) {
        text.append(input);
    }

    public String getText() {
        return text.toString();
    }

    public Snapshot createSnapshot() {
        return new Snapshot(text.toString());
    }

    public void restoreSnapshot(Snapshot snapshot) {
        text.replace(0, text.length(), snapshot.getText());
    }
}
