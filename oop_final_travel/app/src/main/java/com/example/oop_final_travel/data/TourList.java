package com.example.oop_final_travel.data;


import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TourList {
    public static ArrayList<Tour> tours = new ArrayList<>();
    public static Map<String, Integer> place_to_code = new HashMap<>();

    private static boolean isInit = false;

    /*
    private static int orderCount(Context context, int hotelID) {
        String whereClause = OrderContract.OrderEntry.COLUMN_HOTEL_ID + " =?";
        String[] selectionArgs = new String[]{
                String.valueOf(hotelID)
        };
        String[] projection = {
                OrderContract.OrderEntry.COLUMN_ORDER_ID,
        };

        // the cursor from query
        Cursor cursor = context.getContentResolver().query(
                OrderContract.OrderEntry.CONTENT_URI,
                projection,
                whereClause,
                selectionArgs,
                null
        );

        return cursor.getCount();
    }

     */

    // init hotels
    public static void init(Context context) {
        if (isInit) return;
        isInit = true;
        BufferedReader br = null;
        int tour_id = 0;
        try {
            String sCurrentLine;
            br = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("trip_data_all_new.csv")));
            //br = new BufferedReader(new FileReader("new.csv"));
            while ((sCurrentLine = br.readLine()) != null) {
                //Log.d("zzzzzzzzzz", sCurrentLine);
                String[] tokens = sCurrentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);


                String title = tokens[0];//new String(tokens[0].getBytes(), "UTF-8");
                int travel_code = 0;
                String product_key = tokens[2];
                int price = 0;
                String start_date = tokens[4];
                String end_data = tokens[5];
                int lower_bound = 0;
                int upper_bound = 0;
                int count = 0;

                try {
                    double tmp_travel_code = Double.parseDouble(tokens[1]);
                    double tmp_price = Double.parseDouble(tokens[3]);
                    double tmp_lower_bound = Double.parseDouble(tokens[6]);
                    double tmp_upper_bound = Double.parseDouble(tokens[7]);
                    travel_code = (int) tmp_travel_code;
                    price = (int) tmp_price;
                    lower_bound = (int) tmp_lower_bound;
                    upper_bound = (int) tmp_upper_bound;

                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    Log.d("ggggggg", ""+tokens[0]+" "+tokens[1]+" "+tokens[2]+" "+tokens[3]
                            +" "+tokens[4]+" "+tokens[5]+" "+tokens[6]+" "+tokens[7]+" ");
                }
                Tour tour = new Tour(tour_id, title, travel_code, product_key, price, start_date, end_data,
                        lower_bound, upper_bound, count);
                //Log.d("tour_list_test", tour.toString());
                tours.add(tour);
                tour_id += 1;
            }
        } catch (IOException ex) {
            Log.d("zzzzzzzzzz", "gggggggggggggggggggg");
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        try {
            String json = null;
            try {
                InputStream is = context.getAssets().open("travel_code.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JSONArray tour_data = new JSONArray(json);
            for(int i =  0 ; i < tour_data.length() ; ++i) {
                JSONObject parser = tour_data.getJSONObject(i);
                int travel_code = Integer.parseInt(parser.getString("travel_code"));
                String travel_code_names = parser.getString("travel_code_name");
                //Log.d("travel_code_names", ""+ travel_code_names);
                String[] travel_code_name = travel_code_names.split("．");

                for(int j = 0 ; j < travel_code_name.length ; j ++) {
                    //Log.d("travel_code", "code: "+ travel_code + ", names: " + travel_code_name[j]);
                    place_to_code.put(travel_code_name[j], travel_code);
                }
            }

        } catch (JSONException e) {
            Log.e("HotelList", "Problem parsing the hotel JSON results", e);
        }


    }

    // private constructor
    private TourList() { }

}

