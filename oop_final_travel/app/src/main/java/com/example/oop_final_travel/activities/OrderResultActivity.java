package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.oop_final_travel.R;
import com.example.oop_final_travel.data.TourList;

public class OrderResultActivity extends AppCompatActivity {
    private int tour_id,  num_of_people, price;
    private String user_id, order_id, title, date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_result);

        order_id = getIntent().getStringExtra("order_id");
        tour_id = getIntent().getIntExtra("tour_id", 0);
        user_id = getIntent().getStringExtra("user_id");
        num_of_people = getIntent().getIntExtra("num_of_people", 0);

        price = TourList.tours.get(tour_id).price * num_of_people;
        title = TourList.tours.get(tour_id).title;
        date = TourList.tours.get(tour_id).start_date + "~" + TourList.tours.get(tour_id).end_date;


        // show order result
        TextView order_result_order_id = (TextView) findViewById(R.id.order_result_order_id);
        order_result_order_id.setText(order_id);

        TextView order_result_user_id = (TextView) findViewById(R.id.order_result_user_id);
        order_result_user_id.setText(user_id);

        TextView order_result_title = (TextView) findViewById(R.id.order_result_title);
        order_result_title.setText(title);

        TextView order_result_date = (TextView) findViewById(R.id.order_result_date);
        order_result_date.setText(date);

        TextView order_result_num_of_people= (TextView) findViewById(R.id.order_result_num_of_people);
        order_result_num_of_people.setText(String.valueOf(num_of_people));

        TextView order_result_total_price = (TextView) findViewById(R.id.order_result_total_price);
        order_result_total_price.setText(String.valueOf(price));
    }
}
