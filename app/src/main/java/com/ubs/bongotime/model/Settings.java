package com.ubs.bongotime.model;

import com.orm.SugarRecord;

public class Settings extends SugarRecord {

    public int id;
    public String selectedPlayer;
    public String selectedSound;

    public Settings(){
    }

    public Settings(int id, String selectedPlayer, String selectedSound){
        this.id = id;
        this.selectedPlayer = selectedPlayer;
        this.selectedSound = selectedSound;
    }
}
