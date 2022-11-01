package com.hef.review.designpatterns.behavioral.memento.example02;

/**
 * 当前的文本内容
 * @Date 2022/11/1
 * @Author lifei
 */
public class InputText {

    private StringBuilder text = new StringBuilder();

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);
    }

    public void setText(String text) {
        this.text.replace(0, this.text.length(), text);
    }

}
