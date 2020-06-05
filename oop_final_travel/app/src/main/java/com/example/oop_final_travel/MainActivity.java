package com.example.oop_final_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oop_final_travel.activities.SearchOrderActivity;
import com.example.oop_final_travel.activities.SearchTourActivity;
import com.example.oop_final_travel.data.TourList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // init TourList
        TourList.init(this);

        // click listener on search_available
        Button search_tour = (Button) findViewById(R.id.search_tour);
        search_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchTourIntent = new Intent(MainActivity.this, SearchTourActivity.class);
                startActivity(SearchTourIntent);
            }
        });

        Button search_order = (Button) findViewById(R.id.search_order);
        search_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchOrderIntent = new Intent(MainActivity.this, SearchOrderActivity.class);
                startActivity(SearchOrderIntent);
            }
        });
    }
}
