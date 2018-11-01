package com.ubs.bongotime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ubs.bongotime.db.DbManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        DbManager.insertDefaultData();
    }

}
