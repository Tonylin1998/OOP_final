package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oop_final_travel.MainActivity;
import com.example.oop_final_travel.R;
import com.example.oop_final_travel.data.TourList;

public class SearchOrderResultActivity extends AppCompatActivity {
    private int tour_id, price, order_id, user_id, num_of_people;
    String title, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order_result);

        tour_id = getIntent().getIntExtra("tour_id", 0);
        num_of_people = getIntent().getIntExtra("num_of_people", 0);
        order_id = getIntent().getIntExtra("order_id", 0);
        user_id = getIntent().getIntExtra("user_id", 0);

        price = TourList.tours.get(tour_id).price * num_of_people;
        title = TourList.tours.get(tour_id).title;
        date = TourList.tours.get(tour_id).start_date + "~" + TourList.tours.get(tour_id).end_date;



        TextView order_result_order_id = (TextView) findViewById(R.id.order_result_order_id);
        order_result_order_id.setText(String.valueOf(order_id));

        TextView order_result_user_id = (TextView) findViewById(R.id.order_result_user_id);
        order_result_user_id.setText(String.valueOf(user_id));

        TextView order_result_title = (TextView) findViewById(R.id.order_result_title);
        order_result_title.setText(title);

        TextView order_result_date = (TextView) findViewById(R.id.order_result_date);
        order_result_date.setText(date);

        TextView order_result_num_of_people= (TextView) findViewById(R.id.order_result_num_of_people);
        order_result_num_of_people.setText(String.valueOf(num_of_people));

        TextView order_result_total_price = (TextView) findViewById(R.id.order_result_total_price);
        order_result_total_price.setText(String.valueOf(price));


        Button modify_order = (Button) findViewById(R.id.modify_order_button);
        modify_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyOrder();
            }
        });

        Button delete_order = (Button) findViewById(R.id.delete_order_button);
        delete_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrder();
            }
        });
    }
    private void modifyOrder(){
        Intent intent = new Intent(SearchOrderResultActivity.this, ModifyOrderActivity.class);
        intent.putExtra("user_id", user_id);
        intent.putExtra("order_id", order_id);
        intent.putExtra("tour_id", tour_id);
        startActivity(intent);
    }

    private void deleteOrder(){
        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/orders");
        String cmd = "user_id =? AND order_id =?";
        String[] args = new String[]{ Integer.toString(user_id), Integer.toString(order_id) };

        getContentResolver().delete(
                CONTENT_URI,
                cmd,
                args
        );
        Intent intent = new Intent(SearchOrderResultActivity.this, ShowMessageActivity.class);
        intent.putExtra("message", "成功刪除訂單!");
        startActivity(intent);
    }
}
