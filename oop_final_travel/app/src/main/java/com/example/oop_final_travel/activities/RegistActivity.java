package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oop_final_travel.MainActivity;
import com.example.oop_final_travel.R;

public class RegistActivity extends AppCompatActivity {


    private EditText input_user_id, input_password;
    private static String user_id;
    private static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        input_user_id = (EditText) findViewById(R.id.user_id);
        input_password = (EditText) findViewById(R.id.password);

        // click listener on regist
        final Button regist = (Button) findViewById(R.id.regist_button);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });
    }

    /**
     * regist a new user and password
     */
    private void regist() {
        // check user input's format
        user_id = input_user_id.getText().toString();
        if(user_id.length() < 4){
            TextView regist_warning = (TextView)findViewById(R.id.regist_warning);
            regist_warning.setText("帳號長度須大於等於4!");
            return;
        }
        password = input_password.getText().toString();
        if(password.length() < 8){
            TextView regist_warning = (TextView)findViewById(R.id.regist_warning);
            regist_warning.setText("帳號長度須大於等於8!");
            return;
        }


        // check whether the user id is used in database
        // if not, create new data in database
        String cmd = "user_id =? ";
        String[] args = new String[]{user_id};
        String[] projection = {"password"};

        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/login");
        Cursor cursor = getContentResolver().query(
                CONTENT_URI, projection, cmd, args, null);

        int tour_id = 0, num_of_people = 0;
        if (cursor.getCount()>0) {
            TextView regist_warning = (TextView)findViewById(R.id.regist_warning);
            regist_warning.setText("此帳號名稱已被註冊!");
            return;
        }

        ContentValues values = new ContentValues();
        values.put("user_id", user_id);
        values.put("password", password);
        Uri newuri = getContentResolver().insert(CONTENT_URI, values);

        // return to main activity
        Intent intent = new Intent(RegistActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
