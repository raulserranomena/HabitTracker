package com.example.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;

import com.example.habittracker.data.HabitContract.HabitEntry;


public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();


    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habitTracker.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_TIMES + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRow(String habit, int times) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(HabitEntry.COLUMN_HABIT, habit);
        contentValues.put(HabitEntry.COLUMN_TIMES, times);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, contentValues);

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Log.d(LOG_TAG, "insertRow: called,  Error saving the habit in the database.");
        } else {
            // Otherwise, the insertion was successful
            Log.d(LOG_TAG, "insertRow: called, The habits were saved successfully");

        }


    }

    public Cursor readHabits() {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT,
                HabitEntry.COLUMN_TIMES};

        return db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

    }

}
