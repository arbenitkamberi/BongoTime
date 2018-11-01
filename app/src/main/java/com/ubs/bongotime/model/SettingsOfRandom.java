package com.ubs.bongotime.model;

import com.orm.SugarRecord;

/**
 * Model-class for table SETTINGS_OF_RANDOM
 */
public class SettingsOfRandom extends SugarRecord {

    private String soundName;
    private boolean isSelected;

    public SettingsOfRandom(){
    }

    public SettingsOfRandom(String soundName, boolean isSelected){
        this.soundName = soundName;
        this.isSelected = isSelected;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
