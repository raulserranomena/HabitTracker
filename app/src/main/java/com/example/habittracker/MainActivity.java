package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.example.habittracker.data.HabitDbHelper;
import com.example.habittracker.data.HabitContract.HabitEntry;


public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        mDbHelper.insertRow("Pasear a la vakyra", 6);
        mDbHelper.insertRow("Comprar comida", 2);
        mDbHelper.insertRow("Programar", 7);
        mDbHelper.insertRow("Hacer ejercicio", 7);
        mDbHelper.insertRow("Ver anime", 7);


        Cursor cursor = mDbHelper.readHabits();

        int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
        int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT);
        int timesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_TIMES);

        Log.i(LOG_TAG, "Hábitos Semanales: \n");

        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idColumnIndex);
            String currentHabit = cursor.getString(habitColumnIndex);
            int currentTimes = cursor.getInt(timesColumnIndex);

            Log.i(LOG_TAG, "ID: " + currentID + " Hábito: " + currentHabit + " Veces: " + currentTimes);
        }


    }
}