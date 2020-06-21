package com.example.oop_final_travel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oop_final_travel.activities.LoginActivity;
import com.example.oop_final_travel.activities.MainPageActivity;
import com.example.oop_final_travel.activities.RegistActivity;
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

        // click listener on login
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchTourIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(SearchTourIntent);
            }
        });

        // click listener on regist
        Button regist = (Button) findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SearchOrderIntent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(SearchOrderIntent);
            }
        });
    }
}
