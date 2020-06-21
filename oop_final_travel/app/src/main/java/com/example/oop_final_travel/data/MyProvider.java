package com.example.oop_final_travel.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class MyProvider extends ContentProvider {

    private MySQLiteHelper mDbHelper;


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ORDERS = 123;
    private static final int LOGIN = 456;


    @Override
    public boolean onCreate() {
        //getContext().deleteDatabase("Data.db");
        mDbHelper = new MySQLiteHelper(getContext());
        sUriMatcher.addURI("com.example.oop_final_travel", "orders", ORDERS);
        sUriMatcher.addURI("com.example.oop_final_travel", "login", LOGIN);
        return true;
    }

    /**
     * query data from database
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDERS:
                cursor = database.query("orders", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case LOGIN:
                cursor = database.query("login", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * insert data into database
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case ORDERS:
                return insertOrder(uri, values, "orders");
            case LOGIN:
                return insertOrder(uri, values, "login");
        }
        return null;
    }
    private Uri insertOrder(Uri uri, ContentValues values, String table_name){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(table_name, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * delete data from database
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match) {
            case ORDERS:
                rowsDeleted = database.delete("orders", selection, selectionArgs);
                break;
            case LOGIN:
                rowsDeleted = database.delete("login", selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if(rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    /**
     * update the data in database
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDERS:
                return database.update("orders", contentValues, selection, selectionArgs);
            case LOGIN:
                return database.update("login", contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
}
