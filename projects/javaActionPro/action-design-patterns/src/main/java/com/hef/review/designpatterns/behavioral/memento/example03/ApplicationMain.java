package com.hef.review.designpatterns.behavioral.memento.example03;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * 应用
 * @Date 2022/11/2
 * @Author lifei
 */
public class ApplicationMain {

    public static void main(String[] args) {
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        InputText inputText = new InputText();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (StringUtils.equalsIgnoreCase(input, ":list")) {
                System.out.println(inputText.getText());
            }else if (StringUtils.equalsIgnoreCase(input, ":undo")) {
                inputText.restoreSnapshot(snapshotHolder.popSnapshot());
            }else {
                snapshotHolder.pushSnapshot(inputText.createSnapshot());
                inputText.append(input);
            }
        }
    }
}
