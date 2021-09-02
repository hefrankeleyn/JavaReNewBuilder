package com.hef.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

/**
 * @Date 2021/9/1
 * @Author lifei
 */
public class LocalDateTimeMain {

    public static void main(String[] args) {
//        localDateTest();
//        parseTest();
        localDateTimeTest();
        parseDate();
    }

    private static void exchInstant() {
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zoneId).toInstant();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
    }

    private static void parseDate() {
        LocalDate date = LocalDate.now();
        String format = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String format1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String format2 = date.format(dateTimeFormatter);
        System.out.println(format2);
    }

    private static TemporalAdjuster nextWorkingDay2() {
        return TemporalAdjusters.ofDateAdjuster(temporal->{
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int addToAdd = 1;
            if (dayOfWeek==DayOfWeek.FRIDAY) {
                addToAdd = 3;
            }else if (dayOfWeek==DayOfWeek.SATURDAY) {
                addToAdd = 4;
            }
            return temporal.plus(addToAdd, ChronoUnit.DAYS);
        });
    }



    /**
     * 定制下一个工作日
     * @return
     */
    private static TemporalAdjuster nextWorkingDay() {
        return temporal->{
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int addToAdd = 1;
            if (dayOfWeek==DayOfWeek.FRIDAY) {
                addToAdd = 3;
            }else if (dayOfWeek==DayOfWeek.SATURDAY) {
                addToAdd = 4;
            }
            return temporal.plus(addToAdd, ChronoUnit.DAYS);
        };
    }

    private static void temporalAdjuster() {
        LocalDate date = LocalDate.now();
        LocalDate date1 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        LocalDate date2 = date1.with(TemporalAdjusters.lastDayOfMonth());
    }

    private static void localDateOpt2() {
        LocalDate date = LocalDate.now();
        // 加
        LocalDate date2 = date.plusWeeks(1);
        // 减
        LocalDate date3 = date.minusYears(3);
        // 通用 运算
        LocalDate date4 = date.plus(6, ChronoUnit.DAYS);
    }

    private static void localDateOpt() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.withYear(2020);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
    }

    private static void periodTest() {
        LocalDate date1 = LocalDate.of(2021, 9, 13);
        LocalDate date2 = LocalDate.of(2021, 10, 13);
        Period period = Period.between(date1, date2);
    }

    private static void durationTest() {
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(5);
        Duration duration1 = Duration.between(instant1, instant2);

        LocalDateTime dateTime1 = LocalDateTime.of(2021, 9, 8, 9, 23, 12);
        LocalDateTime dateTime2 = LocalDateTime.of(2021, 12, 12, 9, 23, 12);
        Duration duration = Duration.between(dateTime1, dateTime2);

    }

    private static void instantTest() {
        Instant instant = Instant.ofEpochSecond(3);
        Instant instant1 = Instant.ofEpochSecond(3, 0);
        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant3 = Instant.ofEpochSecond(3, -1_000_000_000);

    }

    private static void localDateTimeTest() {
        LocalDateTime dateTime = LocalDateTime.of(2021, 9, 1, 21, 23, 12);
        LocalDate date = LocalDate.of(2021, 9, 1);
        LocalTime time = LocalTime.of(19, 23, 21);
        LocalDateTime dateTime1 = LocalDateTime.of(date, time);

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
