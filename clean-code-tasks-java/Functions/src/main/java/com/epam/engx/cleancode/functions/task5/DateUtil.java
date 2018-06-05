package com.epam.engx.cleancode.functions.task5;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public Date changeToMidnight(Date date, boolean up) {
        if (up) {
            return localDateTimeToDate(atStartOfDay(date).plusDays(1));
        } else {
            return localDateTimeToDate(atStartOfDay(date).minusDays(1));
        }
    }

    public static LocalDateTime atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.with(LocalTime.MIN);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
