package com.example.oop_final_travel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.oop_final_travel.data.TourList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingDeque;

public class Utils {
    /**
     * check date data's format
     */
    public static String parseDate(String raw_date) throws ParseException {
        try {
            // parse date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            Date date = formatter.parse(raw_date);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            return strDate;
        } catch(ParseException e) {
            throw e;
        }
    }

    /**
     * check tour's available number when new order
     * @param context
     * @param tour_id
     * @param num_of_people
     * @return
     */
    public static boolean checkLimit(Context context, int tour_id, int num_of_people){
        String cmd = "tour_id =? ";
        String[] args = new String[]{Integer.toString(tour_id)};
        String[] projection = {"num_of_people"};

        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/orders");
        Cursor cursor = context.getContentResolver().query(
                CONTENT_URI, projection, cmd, args, null);

        int sum = 0;
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()) {
                do {
                    sum += cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        if(TourList.tours.get(tour_id).upper_bound - sum < num_of_people){
            return false;
        }
        return true;
    }

    /**
     * check tour's available number when modify
     * @param context
     * @param order_id
     * @param tour_id
     * @param new_num_of_people
     * @return
     */
    public static boolean checkNewLimit(Context context, int order_id, int tour_id, int new_num_of_people){
        String cmd = "tour_id =? AND order_id !=?";
        String[] args = new String[]{Integer.toString(tour_id), Integer.toString(order_id)};
        String[] projection = {"num_of_people"};

        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/orders");
        Cursor cursor = context.getContentResolver().query(
                CONTENT_URI, projection, cmd, args, null);

        int sum = 0;
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()) {
                do {
                    sum += cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        if(TourList.tours.get(tour_id).upper_bound - sum < new_num_of_people){
            return false;
        }
        return true;
    }



}
