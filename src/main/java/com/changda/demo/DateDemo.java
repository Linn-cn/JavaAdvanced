package com.changda.demo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: JucAndJvm
 * @classname: DateDemo
 * @description:
 * @author: 南街
 * @create: 2019-12-09 11:00
 **/
public class DateDemo {

    @Test
    public void dateFormatter() throws ParseException {
        String ceshi = "2020-04-09";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(ceshi);
    }

    @Test
    public void LocalDateTimeDemo() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate date = LocalDate.of(2018, 8, 8);
        System.out.println(date);
        System.out.println(now.getDayOfYear());
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
    }


    @Test
    public void updateLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        // 修改时间
        LocalDateTime dateTime = now.withYear(9102);
        System.out.println(dateTime);
        System.out.println(now == dateTime);

        System.out.println(now.plusYears(2));
        System.out.println(now.minusYears(10));
    }

    @Test
    public void compareLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        // 比较时间
        LocalDateTime now1 = now.withYear(9102);

        System.out.println(now.isAfter(now1));
        System.out.println(now.isBefore(now1));
        System.out.println(now.isEqual(now1));
    }

    @Test
    public void formatLocalDateTime() {
        // 创建一个日期时间
        LocalDateTime now = LocalDateTime.now();

        // 格式化
//        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd HH时mm分ss秒");
        String format = now.format(dtf);
        System.out.println("format = " + format);
        LocalDateTime inputDateTime = LocalDateTime.parse("2019年12月09 22时11分04秒", dtf);
        System.out.println(inputDateTime);
    }

    @Test
    public void instantDemo() throws InterruptedException {
        // Instant内部保存了秒和纳秒，一般不是给用户使用的，而是方便程序做一些统计。
        Instant instant = Instant.now();
        System.out.println(instant);
//        System.out.println(instant.plusSeconds(2));
        TimeUnit.SECONDS.sleep(3);
        Instant instant1 = Instant.now();
        System.out.println(instant1.getEpochSecond() - instant.getEpochSecond());

//        System.out.println(instant.getEpochSecond());
//        System.out.println(instant.getNano());
    }

    @Test
    public void compareInstant() throws InterruptedException {
        long putDate = Instant.now().toEpochMilli();
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Duration.between(Instant.ofEpochMilli(putDate),Instant.now()).toHours());
    }


    @Test
    public void durationDemo() {
        //Duration 计算时间的距离
        LocalTime now = LocalTime.now();
        LocalTime time = LocalTime.of(14, 3, 5);
        Duration between = Duration.between(now, time);
        System.out.println(between.toDays());
        System.out.println(between.toHours());
        System.out.println(between.toMinutes());
        System.out.println(between.getSeconds());

        //period计算日期的距离
        LocalDate nowDate = LocalDate.now();
        LocalDate date = LocalDate.of(2020, 3, 1);
        Period period = Period.between(date, nowDate);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    @Test
    public void temporalAdjuster() {
        LocalDateTime now = LocalDateTime.now();
        // 自定义日期调整
        TemporalAdjuster firstDayOfNextMonth = temporal -> {
            LocalDateTime dateTime = (LocalDateTime) temporal;
            return dateTime.plusMonths(1).withDayOfMonth(1);
        };

        // JDK自带工具类
//        LocalDateTime newDateTime = now.with(firstDayOfNextMonth);
        LocalDateTime newDateTime = now.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println(newDateTime);
    }


}
