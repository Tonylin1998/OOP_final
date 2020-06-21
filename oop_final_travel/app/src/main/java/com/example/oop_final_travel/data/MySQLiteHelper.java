package com.example.oop_final_travel.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Data.db";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Create database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ORDERS_TABLE =  "CREATE TABLE " + "orders" + " ("
                + "order_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id" + " TEXT NOT NULL, "
                + "tour_id" + " INTEGER NOT NULL, "
                + "num_of_people" + " INTEGER NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ORDERS_TABLE);

        String SQL_CREATE_LOGIN_TABLE =  "CREATE TABLE " + "login" + " ("
                + "user_id" + " TEXT NOT NULL, "
                + "password" + " TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_LOGIN_TABLE);


    }

    /**
     * Upgrade database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + "orders";
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}

