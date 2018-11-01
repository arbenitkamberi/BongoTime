package com.ubs.bongotime;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ubs.bongotime.db.DbManager;
import com.ubs.bongotime.model.Settings;

import java.util.Arrays;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements SensorEventListener {

    private static final String LOG_TAG = "PlayActivity";

    //PROXIMITY-SENSOR INITIALISATION
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;

    //MEDIAPLAYER INITIALISATION
    private List<MediaPlayer> bongoOne;
    private List<MediaPlayer> bongoTwo;
    private List<MediaPlayer> bongoThree;
    private List<MediaPlayer> bongoFour;

    private int bongoCounter = 0;
    private static final int NUMBER_OF_BONGO_PLAYERS = 6;
    //SETTINGS INITIALISATION
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        bongoOne = createListOfMediaplayers(R.raw.bongo1);
        bongoTwo = createListOfMediaplayers(R.raw.bongo2);
        bongoThree = createListOfMediaplayers(R.raw.bongo3);
        bongoFour = createListOfMediaplayers(R.raw.bongo4);

        DbManager.insertDefaultData();
        settings = Settings.listAll(Settings.class).get(0);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mProximity , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){

        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            Log.d(LOG_TAG, "Proximity Sensor: " + event.values[0]);
            if(event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY){
                //near
                System.out.println("near");


                if(settings.getSelectedSound().equals("Bongo1")){
                    bongoOne.get(bongoCounter).start();
                } else if(settings.getSelectedSound().equals("Bongo2")){
                    bongoTwo.get(bongoCounter).start();
                } else if(settings.getSelectedSound().equals("Bongo3")){
                    bongoThree.get(bongoCounter).start();
                } else if(settings.getSelectedSound().equals("Bongo4")){
                    bongoFour.get(bongoCounter).start();
                }

                bongoCounter++;
                if(bongoCounter > 5){
                    bongoCounter = 0;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    /**
     * One MediaPlayer can only play a .mp3 one at a time, to enable faster bongo-playing a list of multiple MediaPlayers is used
     *
     * @param resourceId id of the .mp3
     * @return List of MediaPlayers playing the specified .mp3, list-size same as NUMBER_OF_BONGO_PLAYERS
     */
    private List<MediaPlayer> createListOfMediaplayers(int resourceId){
        MediaPlayer[] mediaPlayers = new MediaPlayer[NUMBER_OF_BONGO_PLAYERS];
        for(int i = 0; i < mediaPlayers.length; i++){
            mediaPlayers[i] = MediaPlayer.create(this, resourceId);
        }
        return Arrays.asList(mediaPlayers);
    }
}
