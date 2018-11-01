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
        Log.d(LOG_TAG, "DbManager erzeugt DbHelper");
        dbHelper = new DbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public static void insertDefaultData(){
        List<Settings> settingsList = Settings.listAll(Settings.class);
        if(settingsList.size() == 0){
            Settings settingsNew = new Settings(1, "Bongo", "Bongo1");
            Settings.save(settingsNew);

            Settings settings = Settings.listAll(Settings.class).get(0);
            SettingsOfRandom bongo1 = new SettingsOfRandom(1,"Bongo1", false);
            SettingsOfRandom bongo2 = new SettingsOfRandom(2,"Bongo2", false);
            SettingsOfRandom bongo3 = new SettingsOfRandom(3,"Bongo3", false);
            SettingsOfRandom bongo4 = new SettingsOfRandom(4,"Bongo4", false);

            SettingsOfRandom.save(bongo1);
            SettingsOfRandom.save(bongo2);
            SettingsOfRandom.save(bongo3);
            SettingsOfRandom.save(bongo4);

        }
    }
}
