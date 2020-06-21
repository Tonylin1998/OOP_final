package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oop_final_travel.R;

public class LoginActivity extends AppCompatActivity {
    private EditText input_user_id, input_password;
    public static String user_id;
    private static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_user_id = (EditText) findViewById(R.id.user_id);
        input_password = (EditText) findViewById(R.id.password);

        // click listener on login
        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * check whether user can login or not
     */
    private void login(){
        // check the user input's format
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

        // check the database's data
        String cmd = "user_id =? AND password =? ";
        String[] args = new String[]{user_id, password};
        String[] projection = {"user_id"};

        Uri CONTENT_URI = Uri.parse("content://com.example.oop_final_travel/login");
        Cursor cursor = getContentResolver().query(
                CONTENT_URI, projection, cmd, args, null);

        int tour_id = 0, num_of_people = 0;
        if (cursor.getCount() == 0) {
            TextView login_warning = (TextView)findViewById(R.id.login_warning);
            login_warning.setText("帳號密碼錯誤!");
            return;
        }

        // login success
        Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
        startActivity(intent);
    }
}
