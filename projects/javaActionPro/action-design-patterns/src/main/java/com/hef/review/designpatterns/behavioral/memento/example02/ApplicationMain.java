package com.hef.review.designpatterns.behavioral.memento.example02;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * 应用
 * @Date 2022/11/1
 * @Author lifei
 */
public class ApplicationMain {

    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (StringUtils.equalsIgnoreCase(input, ":list")) {
                System.out.println(inputText.getText());
            }else if (StringUtils.equalsIgnoreCase(input, ":undo")) {
                InputText snapShot = snapshotHolder.popSnapShot();
                inputText.setText(snapShot.getText());
            }else {
                snapshotHolder.pushSnapshot(inputText);
                inputText.append(input);
            }

        }
    }
}
