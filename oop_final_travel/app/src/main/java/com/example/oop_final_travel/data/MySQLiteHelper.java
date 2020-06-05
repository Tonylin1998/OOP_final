package com.example.oop_final_travel.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {
    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "Data.db";

    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link MySQLiteHelper}.
     *
     * @param context of the app
     */
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("test", "onCreate:");
        // Create a String that contains the SQL statement to create the orders table
        String SQL_CREATE_ORDERS_TABLE =  "CREATE TABLE " + "orders" + " ("
                + "order_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id" + " INTEGER NOT NULL, "
                + "tour_id" + " INTEGER NOT NULL, "
                + "num_of_people" + " INTEGER NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ORDERS_TABLE);
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

