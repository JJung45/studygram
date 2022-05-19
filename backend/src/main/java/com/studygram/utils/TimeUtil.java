package com.studygram.utils;


import java.sql.Date;

public class TimeUtil {
    public static Date getCurrentTime(){
        java.util.Date dt = new java.util.Date();
        java.sql.Date date = new java.sql.Date(dt.getTime());
        return date;
    }
}
