package com.ubs.bongotime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orm.SugarContext;
import com.ubs.bongotime.db.DbManager;
import com.ubs.bongotime.model.Settings;
import com.ubs.bongotime.model.SettingsOfRandom;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "DBACCESS";

    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);
        dbManager = new DbManager(this);

        /*
        List<Settings> settingsList = Settings.listAll(Settings.class);
        if(settingsList.size() == 0){
            Settings settingsNew = new Settings(1, "Bongo", "Bongo1");
            Settings.save(settingsNew);

            Settings settings = Settings.listAll(Settings.class).get(0);
            SettingsOfRandom bongo1 = new SettingsOfRandom("Bongo1", false, settings);
            SettingsOfRandom bongo2 = new SettingsOfRandom("Bongo2", false, settings);
            SettingsOfRandom bongo3 = new SettingsOfRandom("Bongo3", false, settings);
            SettingsOfRandom bongo4 = new SettingsOfRandom("Bongo4", false, settings);

            SettingsOfRandom.save(bongo1);
            SettingsOfRandom.save(bongo2);
            SettingsOfRandom.save(bongo3);
            SettingsOfRandom.save(bongo4);

            settings = Settings.listAll(Settings.class).get(0);
            List<SettingsOfRandom> randoms = SettingsOfRandom.listAll(SettingsOfRandom.class);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbManager.open();
        Log.d(LOG_TAG, "Die Datenquelle wurde geöffnet.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.close();
        Log.d(LOG_TAG, "Die Datenquelle wurde geschlossen.");
    }

    public void play(View v){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void settings(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void howToPlay(View v){
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
}
