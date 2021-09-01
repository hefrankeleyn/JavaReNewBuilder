package com.hef.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;

/**
 * @Date 2021/9/1
 * @Author lifei
 */
public class LocalDateTimeMain {

    public static void main(String[] args) {
//        localDateTest();
        parseTest();
    }

    private static void parseTest() {
        LocalDate date = LocalDate.parse("2021-09-02");
        LocalTime time = LocalTime.parse("23:17:13");
        System.out.println(date);
        System.out.println(time);
    }

    private static void localTimeTest() {
        LocalTime time = LocalTime.of(23, 14, 13);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
    }

    private static void localDateTest() {
        // 创建一个LocalDate
        LocalDate date = LocalDate.of(2021, 9, 1);
        // 年份
        int year = date.getYear();
        // 月分
        Month month = date.getMonth();
        System.out.println(month);
        // 天
        int dayOfMonth = date.getDayOfMonth();
        // 星期几： WEDNESDAY
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // 9 月有31天
        int monthLen = date.lengthOfMonth();
        // 是否为闰年
        boolean leapYear = date.isLeapYear();

        // 从系统中获取当前时间
        LocalDate nowDate = LocalDate.now();
        System.out.println(nowDate);

        int year1 = date.get(ChronoField.YEAR);
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);



        System.out.println(String.format("%d,%s", dayOfMonth, dayOfWeek));
        System.out.println(leapYear);
    }
}
