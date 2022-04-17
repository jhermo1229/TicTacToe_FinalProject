package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "Tictactoe.db";
    public static final int version = 2;
    public static final String TABLE_NAME = "Games";
    public static final String ID = "id";
    public static final String COL_PLAYER_ONE = "player_one";
    public static final String COL_PLAYER_TWO = "player_two";
    public static final String COL_PLAYER_ONE_SCORE = "player_one_score";
    public static final String COL_PLAYER_TWO_SCORE= "player_two_score";
    public static final String COL_TIMESTAMP= "game_timestamp";

    //Create table constant. ID is primary key and auto-incremented
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_PLAYER_ONE + " TEXT NOT NULL, " + COL_PLAYER_TWO + " TEXT, " + COL_PLAYER_ONE_SCORE + " INTEGER, " + COL_PLAYER_TWO_SCORE + " INTEGER, "
            + COL_TIMESTAMP + " TEXT NOT NULL);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    //Constructor for initializing database handler
    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE); //Drop if table exists
        onCreate(sqLiteDatabase);
    }

    public boolean InsertPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase(); // instance of SQL Lite db
        ContentValues contentValues = new ContentValues();

        //COL1 is ID and it is auto-incremented
        contentValues.put(COL_PLAYER_ONE, player.getPlayerOneName());
        contentValues.put(COL_PLAYER_TWO, player.getPlayerTwoName());
        contentValues.put(COL_PLAYER_ONE_SCORE, player.getPlayerOneWin());
        contentValues.put(COL_PLAYER_TWO_SCORE, player.getPlayerTwoWin());
        contentValues.put(COL_TIMESTAMP, player.getCurrentTimeStamp());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        return true;

    }

    //Database call for selecting data in table
    public Cursor viewData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;

            cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        //if data gathered is not null, then will move to first record.
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
