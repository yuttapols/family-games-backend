package com.it.reservation.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Timestamp createTimestmapNow() {

        return new Timestamp(System.currentTimeMillis());
    }

    public static Date createDateTime(Integer value) {
        Calendar cal = Calendar.getInstance();
        // Date today = cal.getTime();
        cal.add(Calendar.DATE, value); // to get previous year add -1
        return cal.getTime();
    }

    public static String dateToString(Date date) {
        if (null != date) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            return formatter.format(date);
        }
        return null;
    }

    public static String timeStampToString(Timestamp time) {
        if (null != time) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            return formatter.format(time);
        }
        return null;
    }
}
