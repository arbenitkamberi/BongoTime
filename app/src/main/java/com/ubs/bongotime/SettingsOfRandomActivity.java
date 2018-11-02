package com.ubs.bongotime;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;

import com.ubs.bongotime.model.SettingsOfRandom;

import java.util.List;

public class SettingsOfRandomActivity extends Dialog implements android.view.View.OnClickListener {

    private Activity activity;
    private Dialog d;
    //private Button save, cancel;
    private CheckBox bongo1, bongo2, bongo3, bongo4;

    public SettingsOfRandomActivity(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings_of_random);
        //save = (Button) findViewById(R.id.btn_save);
        //cancel = (Button) findViewById(R.id.btn_cacncel);
        //save.setOnClickListener(this);
        //cancel.setOnClickListener(this);
        bongo1 = (CheckBox) findViewById(R.id.checkBongo1);
        bongo2 = (CheckBox) findViewById(R.id.checkBongo2);
        bongo3 = (CheckBox) findViewById(R.id.checkBongo3);
        bongo4 = (CheckBox) findViewById(R.id.checkBongo4);

        List<SettingsOfRandom> settingsOfRandom = SettingsOfRandom.listAll(SettingsOfRandom.class);
        for(SettingsOfRandom setting : settingsOfRandom){
            switch (setting.getSoundName()){

            }
        }
    }


    public void save(View view){
        activity.finish();
        dismiss();
    }

    public void cancel(View view){
        dismiss();
    }

    @Override
    public void onClick(View view) {
        /*switch(view.getId()){
            case R.id.btn_yes:
                activity.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();*/
    }
}
