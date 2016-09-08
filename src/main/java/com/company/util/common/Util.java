package com.company.util.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yevhen on 06.09.2016.
 */
public class Util {
    public static Date getDateOnly(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getDateOnly(Timestamp timestamp) {
        return getDateOnly(new Date(timestamp.getTime()));
    }

    public static String decapitalize(String data){
        StringBuilder sb = new StringBuilder(data);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));

        return sb.toString();
    }
}
