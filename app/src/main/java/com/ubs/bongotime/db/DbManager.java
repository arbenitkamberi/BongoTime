package com.ubs.bongotime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ubs.bongotime.model.Settings;
import com.ubs.bongotime.model.SettingsOfRandom;

import java.util.List;

public class DbManager {

    private static final String LOG_TAG = "DBACCESS";

    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public DbManager(Context context){
        Log.d(LOG_TAG, "DbManager creates DbHelper");
        dbHelper = new DbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Reference for database is being requested.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Received database-reference, path for database:" + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "closed database with help from DbManager");
    }

    /**
     * inserts the default settings into the database if there aren't already settings in the database
     */
    public static void insertDefaultData(){
        List<Settings> settingsList = Settings.listAll(Settings.class);
        if(settingsList.size() == 0){
            Settings settingsNew = new Settings("Bongo", "Bongo1");
            Settings.save(settingsNew);
            Log.d(LOG_TAG, "default data saved in Table SETTINGS");

            SettingsOfRandom bongo1 = new SettingsOfRandom("Bongo1", false);
            SettingsOfRandom bongo2 = new SettingsOfRandom("Bongo2", false);
            SettingsOfRandom bongo3 = new SettingsOfRandom("Bongo3", false);
            SettingsOfRandom bongo4 = new SettingsOfRandom("Bongo4", false);
            SettingsOfRandom.save(bongo1);
            SettingsOfRandom.save(bongo2);
            SettingsOfRandom.save(bongo3);
            SettingsOfRandom.save(bongo4);
            Log.d(LOG_TAG, "default data saved in Table SETTINGS_OF_RANDOM");

            Log.d(LOG_TAG, "Default Settings saved in database");
        }
        else {
            Log.d(LOG_TAG, "settings already in database");
        }
    }
}
