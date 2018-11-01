package com.ubs.bongotime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.ubs.bongotime.db.DbManager;
import com.ubs.bongotime.model.Settings;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    private static final String LOG_TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroup = (RadioGroup) findViewById(R.id.bongoGroup);

        DbManager.insertDefaultData();

        //Set the previously selected sound
        Settings settings = Settings.listAll(Settings.class).get(0);
        radioGroup.check(getIdBySoundname(settings.getSelectedSound()));

        //Save in database when user selects a sound
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Settings settings = Settings.listAll(Settings.class).get(0);
                String oldSound = settings.getSelectedSound();
                switch (checkedId){
                    case R.id.bongoOne:
                        settings.setSelectedSound("Bongo1");
                        break;
                    case R.id.bongoTwo:
                        settings.setSelectedSound("Bongo2");
                        break;
                    case R.id.bongoThree:
                        settings.setSelectedSound("Bongo3");
                        break;
                    case R.id.bongoFour:
                        settings.setSelectedSound("Bongo4");
                        break;
                    case R.id.bongoRandom:
                        settings.setSelectedSound("Random");
                        break;
                }
                settings.save();
                Log.d(LOG_TAG, "Bongo sound changed from " + oldSound + " to " + settings.getSelectedSound());
            }
        });
    }

    private int getIdBySoundname(String soundName){
        switch (soundName){
            case "Bongo1": return R.id.bongoOne;
            case "Bongo2": return R.id.bongoTwo;
            case "Bongo3": return R.id.bongoThree;
            case "Bongo4": return R.id.bongoFour;
            case "Random": return R.id.bongoRandom;
        }
        return 0;
    }

}
