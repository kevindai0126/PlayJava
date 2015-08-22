package com.yundai.playjava.playtime;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 * Created by yundai on 2015/6/17.
 */

/**
 Joda 使用以下概念，它们可以应用到任何日期/时间库：
 不可变性（Immutability）
 瞬间性（Instant）
 局部性（Partial）
 年表（Chronology）
 时区（Time zone）
 */
public class PlayJoda {
    public static void main(String[] args) {
        //Sat 01/01/2000 00:00:00.000
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        //Fri 03/31/2000 00:00:00.000
        System.out.println(dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        //Sun 03/19/2000 00:00:00.000
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek()
                .withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        System.out.println();

        //构造DateTime
        // Use a Calendar
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        dateTime = new DateTime(calendar);
        System.out.println(dateTime.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        // Use another Joda DateTime
        DateTime anotherDateTime = dateTime;
        dateTime = new DateTime(anotherDateTime);
        System.out.println(dateTime.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        // Use a String (must be formatted properly)
        String timeString = "2006-01-26T13:30:00-06:00";
        dateTime = new DateTime(timeString);
        System.out.println(dateTime.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        timeString = "2006-01-26";
        dateTime = new DateTime(timeString);
        System.out.println(dateTime.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
        System.out.println();

        LocalDate localDate = new LocalDate(2009, 9, 6);
        System.out.println(localDate);
        LocalTime localTime = new LocalTime(13, 30, 26, 0);
        System.out.println(localTime);
    }
}