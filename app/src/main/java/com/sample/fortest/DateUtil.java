package com.sample.fortest;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /*
    public static void main(String[] args) throws Exception {
        String date = "20180910";
        String result = addDate(date,1,12,1);
        System.out.println(result); }*/
        //년 월 일 날짜 더하기
         //@param dt(날짜) , y(년) , m(월), d(일)
         //@Exam addDate("20180910",1,12,1) -->20200911
         //@return String
    public static String addDate(String dt, int y, int m, int d) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
        Calendar cal = Calendar.getInstance();
        Date date = format.parse(dt);
        cal.setTime(date);
        cal.add(Calendar.YEAR, y);
        //년 더하기
        cal.add(Calendar.MONTH, m); //년 더하기
        cal.add(Calendar.DATE, d); //년 더하기
        return format.format(cal.getTime());
    }

    public static String getDate(String dt, int d) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
        Calendar cal = Calendar.getInstance();
        Date date = format.parse(dt);
        Log.e("text",date.toString());
        Log.e("text",dt);
        cal.setTime(date);
        cal.add(Calendar.DATE, d);
        return format.format(cal.getTime());
    }
}
