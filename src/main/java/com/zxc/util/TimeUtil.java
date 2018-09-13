package com.zxc.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/22.
 */
public class TimeUtil {

    private static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Integer today() {
        LocalDate now = LocalDate.now();
        return Integer.valueOf(now.format(yyyyMMdd));
    }


    public static Integer yesterday() {
        LocalDate now = LocalDate.now().minusDays(1);
        return Integer.valueOf(now.format(yyyyMMdd));
    }

    public static long weekLater() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weeksLater = LocalDate.now().plusDays(7).atStartOfDay();
        Duration between = Duration.between(now, weeksLater);
        return between.getSeconds();
    }

    /**
     * 日常任务过期时间
     *
     * @return
     */
    public static long dailyExpireTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
        Duration between = Duration.between(now, tomorrow);
        return between.getSeconds();
    }

    /**
     * 签到过期时间
     *
     * @return
     */
    public static long signExpireTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDate.now().plusDays(2).atStartOfDay();
        Duration between = Duration.between(now, tomorrow);
        return between.getSeconds();
    }

    /**
     * @param createTime 10位的时间戳
     * @return
     */
    public static LocalDate parseMills2LocalDateNow(String createTime) {
        try {
            Date date = new Date(Long.valueOf(createTime) * 1000L);
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            throw new RuntimeException("时间转化失败",e);
        }
    }

    public static Date getTime() {
//        "2018-07-01 21:01";
        Date date = null;
        Date now = new Date();
        String nows = sdf.format(now);
        try {
            date = sdf.parse(nows);
        } catch (ParseException e) {
            throw new RuntimeException("时间转换失败",e);
        }
        return date;
    }


    public static void main(String[] args) {
        Date date = null;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nows = sdf.format(now);
        System.out.println("nows=" + nows);
        try {
            date = sdf.parse(nows);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("aa=" + sdf.format(date));
    }

    public static String formatDate(Date cTime) {
        return sdf.format(cTime);
    }


    public static String nowDateStr() {
        return sdf.format(new Date());
    }

    public static Date parseDate(String s) {
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException("时间解析失败");
        }
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
}
