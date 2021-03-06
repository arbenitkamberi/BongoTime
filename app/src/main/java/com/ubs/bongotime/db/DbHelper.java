package com.ubs.bongotime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBACCESS";

    public static final String DB_NAME = "BongoTest12.db";
    public static final int DB_VERSION = 1;

    //Table Settings
    public static final String TABLE_SETTINGS = "settings";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SELECTED_PLAYER = "selectedPlayer";
    public static final String COLUMN_SELECTED_SOUND = "selectedSound";

    public static final String SQL_CREATE_SETTINGS = "CREATE TABLE SETTINGS ( `ID` INTEGER, `SELECTED_PLAYER` TEXT, `SELECTED_SOUND` TEXT, PRIMARY KEY(`ID`) );";



    //Table SettingsOfRandom
    public static final String TABLE_SETTINGS_OF_RANDOM = "SettingsOfRandom";
    public static final String COLUMN_ID_OF_RANDOM = "id";
    public static final String COLUMN_SOUND_NAME = "soundName";
    public static final String COLUMN_IS_SELECTED = "isSelected";

    public static final String SQL_CREATE_SETTINGS_OF_RANDOM = "CREATE TABLE SETTINGS_OF_RANDOM ( `ID` INTEGER, `SOUND_NAME` TEXT NOT NULL, `IS_SELECTED` INTEGER DEFAULT 0, PRIMARY KEY(`ID`) );";


    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper created the database " + getDatabaseName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_SETTINGS);
            Log.d(LOG_TAG, "Table SETTINGS was created with sql-query: " + SQL_CREATE_SETTINGS);

            db.execSQL(SQL_CREATE_SETTINGS_OF_RANDOM);
            Log.d(LOG_TAG, "Table SETTINGS_OF_RANDOM was created with sql-query: " + SQL_CREATE_SETTINGS);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Error creating table: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e(LOG_TAG, "onUpgrade was called" );
    }
}
