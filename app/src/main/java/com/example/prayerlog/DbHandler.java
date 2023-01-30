package com.example.prayerlog;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.FaceDetector;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Prayers.db";
    private static final String TABLE_NAME = "Prayers";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_PRAY = "Pray";
    private static final String COLUMN_BAJAMAT = "Bajamat";
    private static final String COLUMN_RAKATS = "NumOfRakats";

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_PRAY + " INTEGER,"
                + COLUMN_BAJAMAT + " INTEGER,"
                + COLUMN_RAKATS + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertPrayer(Prayer prayer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, prayer.getPrayerName());
        values.put(COLUMN_DATE, prayer.getPrayerDate());
        values.put(COLUMN_PRAY, prayer.getPray());
        values.put(COLUMN_BAJAMAT, prayer.getBajamat());
        values.put(COLUMN_RAKATS, prayer.getNumOfRakats());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Prayer> selectAllPrayers() {
        List<Prayer> prayers = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst())
        {
            do{
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                @SuppressLint("Range") Boolean pray = cursor.getInt(cursor.getColumnIndex(COLUMN_PRAY)) == 1? Boolean.TRUE: Boolean.FALSE;
                @SuppressLint("Range") Boolean bajamat = cursor.getInt(cursor.getColumnIndex(COLUMN_BAJAMAT)) == 1? Boolean.TRUE: Boolean.FALSE;
                @SuppressLint("Range") int rakats = cursor.getInt(cursor.getColumnIndex(COLUMN_RAKATS));
                prayers.add(new Prayer(name, date, pray, bajamat,rakats));
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return prayers;
    }
}
