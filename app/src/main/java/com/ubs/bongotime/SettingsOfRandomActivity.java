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
    private Button save, cancel;
    private CheckBox bongo1, bongo2, bongo3, bongo4;
    private List<SettingsOfRandom> settingsOfRandom;

    public SettingsOfRandomActivity(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings_of_random);

        save = (Button) findViewById(R.id.btn_save);
        cancel = (Button) findViewById(R.id.btn_cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        bongo1 = (CheckBox) findViewById(R.id.checkBongo1);
        bongo2 = (CheckBox) findViewById(R.id.checkBongo2);
        bongo3 = (CheckBox) findViewById(R.id.checkBongo3);
        bongo4 = (CheckBox) findViewById(R.id.checkBongo4);

        settingsOfRandom = SettingsOfRandom.listAll(SettingsOfRandom.class);
        for(SettingsOfRandom setting : settingsOfRandom){
            if(setting.isSelected()) {
                switch (setting.getSoundName()) {
                    case "Bongo1":
                        bongo1.setChecked(true);
                        break;
                    case "Bongo2":
                        bongo2.setChecked(true);
                        break;
                    case "Bongo3":
                        bongo3.setChecked(true);
                        break;
                    case "Bongo4":
                        bongo4.setChecked(true);
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_save){ //User pressed "save"
            for(SettingsOfRandom setting : settingsOfRandom){
                switch (setting.getSoundName()){
                    case "Bongo1":
                        setting.setSelected(bongo1.isChecked());
                        break;
                    case "Bongo2":
                        setting.setSelected(bongo2.isChecked());
                        break;
                    case "Bongo3":
                        setting.setSelected(bongo3.isChecked());
                        break;
                    case "Bongo4":
                        setting.setSelected(bongo4.isChecked());
                        break;
                }
                setting.save();
            }
        }

        dismiss();
    }
}
