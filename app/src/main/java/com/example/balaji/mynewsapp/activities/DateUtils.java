package com.example.balaji.mynewsapp.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {
    public static String formatNewsApiDate(String inputDate) {
        try {
            String inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            String outputDateFormat = "EEE, d MMM yyyy KK:mm";

            SimpleDateFormat input = new SimpleDateFormat(inputDateFormat);
            SimpleDateFormat output = new SimpleDateFormat(outputDateFormat);


            Date date = input.parse(inputDate);
            return output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inputDate;
    }
}
