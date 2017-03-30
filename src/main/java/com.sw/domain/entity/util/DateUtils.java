package com.sw.domain.entity.util;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static Date addMonth(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    public static Date addDate(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    public static List<Date> getStartAndEnd(String year, String month)
    {
        Date start = null;
        Date end = null;
        if(month != null && month.length() > 0){
            if(month.length() == 1){
                month = "0" + month;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                start = sdf.parse(year + "-" + month);
                end = DateUtils.addMonth(start, 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            try {
                start = sdf.parse(year);
                end = DateUtils.addMonth(start, 12);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return Lists.newArrayList(start, end);
    }
    public static List<Date> getStartAndEndDate(String year, String month, String date)
    {
        Date start = null;
        Date end = null;
        if(date != null && date.length() > 0){
            if(date.length() == 1){
                date = "0" + date;
            }
            if(month.length() == 1){
                month = "0" + month;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                start = sdf.parse(year + "-" + month+"-"+date);
                end = addDate(start,1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            if(month.length() == 1){
                month = "0" + month;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                start = sdf.parse(year + "-" + month);
                end = DateUtils.addMonth(start, 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return Lists.newArrayList(start, end);
    }

    /**
     * 判断传入时间字符串+limitMonth 与当前日期比较是否超过当前日期，超过返回false，没超过返回true；
     * @param timeStr
     * @param limitMonth
     * @return
     */
    public static boolean isOverPeriodOfTime(String timeStr,int limitMonth){
        boolean isOk = true;
        Date nowDate = new Date();
        Date templeDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            templeDate = sdf.parse(timeStr);
            templeDate = addMonth(templeDate,limitMonth);
            if(nowDate.after(templeDate)){
                isOk = true;
            }else {
                isOk = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isOk;
    }

    /**
     * 根据传入的时分，返回大写的时间
     * @param hours
     * @param minutes
     * @return
     */
    public static String getUpperTime(String hours,String minutes){
        char[] upper = "零一二三四五六七八九十".toCharArray();
        StringBuilder sb = new StringBuilder();

        int hour = Integer.parseInt(hours);
        if(hour > 0 && hour <= 10){
            sb.append(upper[hour] + "时");
        }else if(hour > 10 && hour < 20){
            sb.append("十" + upper[hour%10] + "时");
        }else if(hour >= 20){
            sb.append("二十");
            if(hours.substring(1,2).equals("0")){
                sb.append("时");
            }else{
                sb.append(upper[hour%20] + "时");
            }
        }

        int minute = Integer.parseInt(minutes);
        if(minute > 0 && minute <= 10){
            sb.append(upper[minute] + "分");
        }else if(minute > 10 && minute < 20){
            sb.append("十" + upper[minute%10] + "分");
        }else if(minute >= 20){
            if(!minutes.substring(1,2).equals("0")){
                sb.append(upper[Integer.parseInt(minutes.substring(0,1))]
                        + "十"
                        + upper[Integer.parseInt(minutes.substring(1,2))]
                        + "分");
            }else{
                sb.append(upper[Integer.parseInt(minutes.substring(0,1))] + "十分");
            }
        }

        return sb.toString();
    }

    public static Date  formatDate(String d) {
        SimpleDateFormat sdfWithDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfNoDay = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            if (d.length() < 8) {
                date = sdfNoDay.parse(d);
            } else {
                date = sdfWithDay.parse(d);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date formatEndDate(String d) {
        SimpleDateFormat sdfWithDay = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfNoDay = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            if (d.length() < 8) {
                date = sdfNoDay.parse(d);
            } else {
                date = sdfWithDay.parse(d);
                date = DateUtils.addDate(date, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 解析时间，异常时返回null
     * @param date
     * @param format
     * @return
     */
    public static Date parseDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
