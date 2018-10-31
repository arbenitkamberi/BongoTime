package com.ubs.bongotime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ubs.bongotime.model.Settings;

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBACCESS";

    public static final String DB_NAME = "BongoTest5.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_SETTINGS = "settings";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SELECTED_PLAYER = "selectedPlayer";
    public static final String COLUMN_SELECTED_SOUND = "selectedSound";
    public static final String COLUMN_SETTINGS_RANDOM = "settingsRandom";

    public static final String SQL_CREATE_SETTINGS = "CREATE TABLE SETTINGS ( `ID` INTEGER NOT NULL, `SELECTED_PLAYER` TEXT NOT NULL DEFAULT 'Bongo', `SELECTED_SOUND` TEXT NOT NULL DEFAULT 'Bongo1', `SETTINGS_RANDOM` TEXT NOT NULL DEFAULT '', PRIMARY KEY(`ID`) )";

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE_SETTINGS);

            Settings settingsNew = new Settings();
            Settings.save(settingsNew);

            Log.d(LOG_TAG, "Die Tabelle USER wird mit SQL-Befehl: " + SQL_CREATE_SETTINGS + " angelegt.");
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e(LOG_TAG, "onUpgrade wurde aufgerufen" );
    }
}
