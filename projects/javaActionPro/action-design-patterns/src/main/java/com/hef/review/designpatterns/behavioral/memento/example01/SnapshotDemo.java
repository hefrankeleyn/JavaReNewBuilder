package com.hef.review.designpatterns.behavioral.memento.example01;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 2022/11/1
 * @Author lifei
 */
public class SnapshotDemo {

    private static List<String> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
           String oneLine = scanner.nextLine();
           if (StringUtils.equalsIgnoreCase(oneLine, ":list")) {
               for (String s : result) {
                   System.out.println(s);
               }
           }else if (StringUtils.equalsIgnoreCase(oneLine, ":undo")) {
               if (result.size()>0) {
                   result.remove(result.size()-1);
               }
           }else {
               result.add(oneLine);
           }
        }
    }
}
