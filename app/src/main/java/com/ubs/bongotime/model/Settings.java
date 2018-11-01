package com.ubs.bongotime.model;

import com.orm.SugarRecord;

public class Settings extends SugarRecord {

    private String selectedPlayer;
    private String selectedSound;

    public Settings(){
    }

    public Settings(String selectedPlayer, String selectedSound){
        this.selectedPlayer = selectedPlayer;
        this.selectedSound = selectedSound;
    }

    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(String selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public String getSelectedSound() {
        return selectedSound;
    }

    public void setSelectedSound(String selectedSound) {
        this.selectedSound = selectedSound;
    }
}
