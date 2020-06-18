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

    // variable for database helper
    private MySQLiteHelper mDbHelper;

    // variable for uri matcher and match code
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int ORDERS = 100;
    private static final int LOGIN = 101;

    // Initialize variable we need
    @Override
    public boolean onCreate() {
        //getContext().deleteDatabase("Data.db");

        mDbHelper = new MySQLiteHelper(getContext());
        sUriMatcher.addURI("com.example.oop_final_travel", "orders", ORDERS);
        sUriMatcher.addURI("com.example.oop_final_travel", "login", LOGIN);
        return true;
    }

    /**
     *
     * Query from database
     *
     * @param uri the uri of table
     * @param projection the columns we want
     * @param selection the range of what we confine
     * @param selectionArgs the arguments of range clause
     * @param sortOrder the sort order
     * @return the cursor we query
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // variables for database, cursor, and the match code
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

    // We didn't use it
    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * Insert into database
     *
     * @param uri the uri of table
     * @param values what we want to insert
     * @return a new uri of the row we insert
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

    /**
     * the helping method of insertion
     *
     * @param uri the uri of table
     * @param values what we want to insert
     * @return a new uri of the row we insert
     */
    private Uri insertOrder(Uri uri, ContentValues values, String table_name){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(table_name, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Deletion of database
     *
     * @param uri the uri of the table
     * @param selection the range of what we confine
     * @param selectionArgs the arguments of range clause
     * @return return the rows being deleted
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
     * Update three types of rooms and the days they stayed
     * specified in the selection and selection arguments (which could be 0 or 1 or more rooms).
     * Return the number of rows that were successfully updated.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ORDERS:
                return database.update("orders", contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
}
