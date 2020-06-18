package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oop_final_travel.Utils;
import com.example.oop_final_travel.data.TourList;
import com.example.oop_final_travel.R;

public class OrderActivity extends AppCompatActivity {
    private EditText  input_num_of_people;
    private int tour_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tour_id = getIntent().getIntExtra("tour_id", 0);

        String title = TourList.tours.get(tour_id).title;
        String date = TourList.tours.get(tour_id).start_date + "~" + TourList.tours.get(tour_id).end_date;


        TextView order_text_title = (TextView)findViewById(R.id.order_text_title);
        order_text_title.setText(title);
        TextView order_date = (TextView)findViewById(R.id.order_date);
        order_date.setText(date);


        //input_user_id = (EditText) findViewById(R.id.user_id);
        input_num_of_people = (EditText) findViewById(R.id.num_of_people);

        Button order = (Button) findViewById(R.id.order_button2);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newOrder();
            }
        });
    }

    /**
     * search tour
     */
    private void newOrder(){
        int user_id, num_of_people;

        // parse user inputs
        /*
        try {
            user_id = Integer.parseInt(input_user_id.getText().toString());
        } catch (NumberFormatException e) {
            TextView order_tour_warning = (TextView)findViewById(R.id.order_tour_warning);
            order_tour_warning.setText("使用者ID格式錯誤!");
            return;
        }
        */

        // parse user inputs
        try {
            num_of_people = Integer.parseInt(input_num_of_people.getText().toString());
        } catch (NumberFormatException e) {
            TextView order_tour_warning = (TextView) findViewById(R.id.order_tour_warning);
            order_tour_warning.setText("人數格式錯誤 !");
            return;
        }
        if(num_of_people <= 0) {
            TextView order_tour_warning = (TextView) findViewById(R.id.order_tour_warning);
            order_tour_warning.setText("人數需大於0 !");
            return;
        }

        if(!Utils.checkLimit(this, tour_id, num_of_people)){
            TextView order_tour_warning = (TextView) findViewById(R.id.order_tour_warning);
            order_tour_warning.setText("剩餘數量不足 !");
            return;
        }

        // create new order
        ContentValues values = new ContentValues();
        values.put("user_id", LoginActivity.user_id);
        values.put("tour_id", tour_id);
        values.put("num_of_people", num_of_people);


        // insert into database
        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/orders");
        Uri newuri = getContentResolver().insert(CONTENT_URI, values);




        Intent intent = new Intent(OrderActivity.this, OrderResultActivity.class);
        intent.putExtra("order_id", newuri.getLastPathSegment());
        intent.putExtra("tour_id", tour_id);
        intent.putExtra("user_id", LoginActivity.user_id);
        intent.putExtra("num_of_people", num_of_people);
        startActivity(intent);


    }
}
