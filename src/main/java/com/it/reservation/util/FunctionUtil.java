package com.it.reservation.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class FunctionUtil {

    public static String genarateCustomerNo(Long roleId, Long userId) {

        StringBuilder result = new StringBuilder();

        Calendar today = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        String date = format1.format(today.getTime());

        result.append(Constants.INITIALS_NAME_PROJECT);
        result.append(String.format("%03d", Integer.parseInt(roleId.toString())));
        result.append(date);
        result.append(String.format("%03d", Integer.parseInt(userId.toString())));

        return result.toString();
    }

    public static String genarateKeyNumber(Integer tableSeq, String prefix) {

        StringBuilder result = new StringBuilder();

        Calendar today = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        String date = format1.format(today.getTime());

        result.append(prefix);
        result.append(generateRandomChars());
        result.append(date);
        result.append(String.format("%04d", (tableSeq + 1)));

        return result.toString();
    }

    public static String genarateCustomerNo(Integer tableSeq, String prefix) {

        StringBuilder result = new StringBuilder();

        // Calendar today = Calendar.getInstance();
        // SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        // String date = format1.format(today.getTime());
        result.append(prefix);
        result.append(generateRandomChars());
        // result.append(date);
        result.append(String.format("%04d", (tableSeq + 1)));

        return result.toString();
    }

    public static String generateRandomChars() {
        StringBuilder sb = new StringBuilder();
        String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int length = 7;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }
}
