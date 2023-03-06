package com.tarasov.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperClass {
    public static String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
