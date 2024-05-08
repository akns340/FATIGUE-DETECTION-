package com.example.hardik.automateddriverdrowsinesscontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sleep_records.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SLEEP = "sleep_records";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_START_TIME = "start_time";
    private static final String COLUMN_END_TIME = "end_time";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_QUALITY = "quality";

    // SQL statement to create the sleep_records table
    private static final String SQL_CREATE_SLEEP_TABLE =
            "CREATE TABLE " + TABLE_SLEEP + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_START_TIME + " INTEGER," +
                    COLUMN_END_TIME + " INTEGER," +
                    COLUMN_DURATION + " INTEGER," +
                    COLUMN_QUALITY + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SLEEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement if you need to upgrade the database schema in the future
    }

    // Method to insert a sleep record into the database
    public long insertSleepRecord(SleepData sleepData) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a ContentValues object to store the data
        ContentValues values = new ContentValues();
        values.put(COLUMN_START_TIME, sleepData.getStartTime());
        values.put(COLUMN_END_TIME, sleepData.getEndTime());
        values.put(COLUMN_DURATION, sleepData.getDuration());
        values.put(COLUMN_QUALITY, sleepData.getQuality());
        // Insert the data into the database
        long newRowId = db.insert(TABLE_SLEEP, null, values);
        db.close();
        return newRowId;
    }

    // Method to retrieve all sleep start times from the database
    public List<Long> getSleepStartTimes() {
        List<Long> sleepStartTimes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SLEEP,
                new String[]{COLUMN_START_TIME},
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long startTime = cursor.getLong(cursor.getColumnIndex(COLUMN_START_TIME));
                sleepStartTimes.add(startTime);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return sleepStartTimes;
    }

    // Method to retrieve all sleep records from the database
    private List<SleepData> getAllSleepRecords() {
        List<SleepData> sleepRecords = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SLEEP,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long startTime = cursor.getLong(cursor.getColumnIndex(COLUMN_START_TIME));
                long endTime = cursor.getLong(cursor.getColumnIndex(COLUMN_END_TIME));
                long duration = cursor.getLong(cursor.getColumnIndex(COLUMN_DURATION));
                int quality = cursor.getInt(cursor.getColumnIndex(COLUMN_QUALITY));
                SleepData sleepData = new SleepData(startTime, endTime, duration, quality);
                sleepRecords.add(sleepData);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return sleepRecords;
    }

    // Method to calculate the average sleep duration
    public double calculateAverageSleepPercentage() {
        // Total time in a day (in milliseconds)
        long totalDayTime = 24 * 60 * 60 * 1000; // 24 hours

        // Get the total sleep duration
        List<SleepData> sleepRecords = getAllSleepRecords();
        long totalSleepTime = 0;
        for (SleepData record : sleepRecords) {
            totalSleepTime += record.getDuration();
        }

        // Calculate the sleep percentage
        double sleepPercentage = ((double) totalSleepTime / totalDayTime) * 100;

        return sleepPercentage;
    }

    // Method to calculate the predicted sleep onset time
    public long calculateAverageSleepOnsetTime() {
        List<Long> sleepStartTimes = getSleepStartTimes();
        if (sleepStartTimes.isEmpty()) {
            return 0; // Return default value if no data available
        }

        long totalStartTime = 0;
        for (long startTime : sleepStartTimes) {
            totalStartTime += startTime;
        }

        return totalStartTime / sleepStartTimes.size();
    }
}
