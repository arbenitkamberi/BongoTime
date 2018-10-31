package com.ubs.bongotime.model;

import com.orm.SugarRecord;

import java.util.Arrays;
import java.util.List;


public class Settings extends SugarRecord {

    public int id;
    public String selectedPlayer;
    public String selectedSound;
    public String settingsRandom;

    public Settings(){
    }

    public List<String> getSettingsRandomList(){
        return Arrays.asList(settingsRandom.split(","));
    }
}
