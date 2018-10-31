package com.ubs.bongotime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orm.SugarContext;
import com.ubs.bongotime.db.DbManager;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "DBACCESS";

    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);
        dbManager = new DbManager(this);
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
