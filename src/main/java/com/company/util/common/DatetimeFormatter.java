package com.company.util.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeFormatter {
    private static final String DEFAULT_DATE_FORMAT = "dd-MM-yyyy";

    private static SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

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

    public static String DateToStringPresentation(Date date) {
        return defaultDateFormat.format(date);
    }

    public static Date parseDateFromStringPresentation(String datePresentation) {
        try {
            return (datePresentation == null || datePresentation.trim().isEmpty()) ? null :
                    defaultDateFormat.parse(datePresentation);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}