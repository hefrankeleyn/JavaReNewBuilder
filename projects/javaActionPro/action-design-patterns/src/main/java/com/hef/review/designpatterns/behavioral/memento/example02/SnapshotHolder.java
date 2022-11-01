package com.hef.review.designpatterns.behavioral.memento.example02;

import java.util.Stack;

/**
 * 历史内容备份
 * @Date 2022/11/1
 * @Author lifei
 */
public class SnapshotHolder {

    private Stack<InputText> snapshots = new Stack<>();

    public InputText popSnapShot() {
        return snapshots.pop();
    }

    public void pushSnapshot(InputText inputText) {
        InputText deepClonedInputText = new InputText();
        deepClonedInputText.setText(inputText.getText());
        snapshots.push(deepClonedInputText);
    }
}
