package com.company.util.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yevhen on 06.09.2016.
 */
public class Util {

    public static Date getDateOnly(Timestamp timestamp) {
        Date date = new Date(timestamp.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
