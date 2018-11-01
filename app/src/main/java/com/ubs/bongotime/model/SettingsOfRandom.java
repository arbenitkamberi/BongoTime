package com.ubs.bongotime.model;

import com.orm.SugarRecord;

public class SettingsOfRandom extends SugarRecord {

    public int id;
    public String soundName;
    public boolean isSelected;

    public SettingsOfRandom(){

    }

    public SettingsOfRandom(int id, String soundName, boolean isSelected){
        this.id = id;
        this.soundName = soundName;
        this.isSelected = isSelected;
    }
}
