package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oop_final_travel.R;
import com.example.oop_final_travel.Utils;

import java.text.ParseException;

import static com.example.oop_final_travel.data.TourList.place_to_code;

public class SearchOrderActivity extends AppCompatActivity {

    private EditText input_order_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);



        //input_user_id = (EditText) findViewById(R.id.user_id);
        input_order_id = (EditText) findViewById(R.id.order_id);

        // click listener on order
        Button order = (Button) findViewById(R.id.order_button3);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchOrder();
            }
        });
    }

    /**
     * search order
     */
    private void searchOrder(){
        int user_id, order_id;
        // parse user inputs
        /*
        try {
            user_id = Integer.parseInt(input_user_id.getText().toString());
        } catch (NumberFormatException e) {
            TextView search_order_warning = (TextView)findViewById(R.id.search_order_warning);
            search_order_warning.setText("使用者ID格式錯誤!");
            return;
        }
        */

        // check user input's format
        try {
            order_id = Integer.parseInt(input_order_id.getText().toString());
        } catch (NumberFormatException e) {
            TextView search_order_warning = (TextView)findViewById(R.id.search_order_warning);
            search_order_warning.setText("訂單編號格式錯誤!");
            return;
        }

        // search in database
        String cmd = "user_id =? AND order_id =? ";
        String[] args = new String[]{LoginActivity.user_id, Integer.toString(order_id)};
        String[] projection = {"tour_id", "num_of_people"};

        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/orders");
        Cursor cursor = getContentResolver().query(
                CONTENT_URI, projection, cmd, args, null);

        int tour_id = 0, num_of_people = 0;
        if (cursor.getCount()>1) {
            TextView search_order_warning = (TextView)findViewById(R.id.search_order_warning);
            search_order_warning.setText("error ! Multiple data !");
            return;
        }
        else if (cursor.moveToFirst()) {
            do {
                Log.d("test", "checkLimit: "+cursor.getInt(0));
                tour_id = cursor.getInt(0);
                num_of_people = cursor.getInt(1);
            } while (cursor.moveToNext());
       }
        else{
            TextView search_order_warning = (TextView)findViewById(R.id.search_order_warning);
            search_order_warning.setText("查無資料 !");
            return;
        }

        // if data has been searched, pass information to SearchOrderResultActivity
        Intent intent = new Intent(SearchOrderActivity.this, SearchOrderResultActivity.class);
        intent.putExtra("tour_id", tour_id);
        intent.putExtra("user_id", LoginActivity.user_id);
        intent.putExtra("order_id", order_id);
        intent.putExtra("num_of_people", num_of_people);
        startActivity(intent);
    }
}
