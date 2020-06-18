package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oop_final_travel.R;
import com.example.oop_final_travel.Utils;

public class ModifyOrderActivity extends AppCompatActivity {
    private int order_id, tour_id, new_num_of_people;
    private String user_id;
    EditText new_num_of_people_input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_order);

        user_id = getIntent().getStringExtra("user_id");
        order_id = getIntent().getIntExtra("order_id", 0);
        tour_id = getIntent().getIntExtra("tour_id", 0);

        new_num_of_people_input = (EditText) findViewById(R.id.new_num_of_people);


        Button order = (Button) findViewById(R.id.modify_button);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyOrder();
            }
        });
    }
    private void modifyOrder(){
        int new_num_of_people;
        // parse user inputs
        try {
            new_num_of_people = Integer.parseInt(new_num_of_people_input.getText().toString());
        } catch (NumberFormatException e) {
            TextView modify_warning = (TextView)findViewById(R.id.modify_warning);
            modify_warning.setText("人數格式錯誤 !");
            return;
        }
        if(new_num_of_people <= 0) {
            TextView modify_warning = (TextView) findViewById(R.id.modify_warning);
            modify_warning.setText("人數需大於0 !");
            return;
        }

        if(!Utils.checkNewLimit(this, order_id, tour_id, new_num_of_people)){
            TextView order_tour_warning = (TextView) findViewById(R.id.order_tour_warning);
            order_tour_warning.setText("剩餘數量不足 !");
            return;
        }

        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/orders");
        String cmd = "user_id =? AND order_id =?";
        String[] args = new String[]{ user_id, Integer.toString(order_id) };
        ContentValues values = new ContentValues();
        values.put("num_of_people", new_num_of_people);

        int rowsUpdated = getContentResolver().update(
                CONTENT_URI,
                values,
                cmd,
                args
        );
        if(rowsUpdated != 0) {
            Intent intent = new Intent(ModifyOrderActivity.this, ShowMessageActivity.class);
            intent.putExtra("message", "成功修改訂單!");
            startActivity(intent);
        }
    }
}
