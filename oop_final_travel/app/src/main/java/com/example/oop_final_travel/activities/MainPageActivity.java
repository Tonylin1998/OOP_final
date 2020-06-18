package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oop_final_travel.MainActivity;
import com.example.oop_final_travel.R;
import com.example.oop_final_travel.data.TourList;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // click listener on search_available
        Button search_tour = (Button) findViewById(R.id.search_tour);
        search_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchTourIntent = new Intent(MainPageActivity.this, SearchTourActivity.class);
                startActivity(SearchTourIntent);
            }
        });

        Button search_order = (Button) findViewById(R.id.search_order);
        search_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchOrderIntent = new Intent(MainPageActivity.this, SearchOrderActivity.class);
                startActivity(SearchOrderIntent);
            }
        });

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.user_id = null;
                Intent SearchOrderIntent = new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(SearchOrderIntent);
            }
        });
    }




}
