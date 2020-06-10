package com.example.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    public HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public static final String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of the habit.
         * Type: TEXT
         */
        public static final String COLUMN_HABIT = "hábito";


        /**
         * Times at week of the habit.
         * Type: INTEGER
         */
        public static final String COLUMN_TIMES = "veces";


    }


}
