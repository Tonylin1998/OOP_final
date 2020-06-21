package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.oop_final_travel.R;

public class ShowMessageActivity extends AppCompatActivity {
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        // shoe message
        message = getIntent().getStringExtra("message");
        TextView message_view = (TextView)findViewById(R.id.message);
        message_view.setText(message);

    }
}
