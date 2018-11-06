package com.ubs.bongotime;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ubs.bongotime.db.DbManager;
import com.ubs.bongotime.model.Settings;
import com.ubs.bongotime.model.SettingsOfRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private List<List<MediaPlayer>> bongos; //List containing bongoOne to bongoFour

    //BONGOCOUNTER used to not always use same mediaplayer on same bongo-sound
    private int bongoCounter = 0;
    private static final int NUMBER_OF_BONGO_PLAYERS = 9;

    //SETTINGS INITIALISATION
    private Settings settings;

    //COMPONENTS
    private ImageView bongoPlay;
    private ImageView DKPlayLeft;
    private ImageView DKPlayRight;
    private ImageView catUp;
    private ImageView catDown;

    private Timer timer;
    private Handler handler;

    private boolean bongoIsChosen;
    private boolean catIsChosen;

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
        bongos = new ArrayList<List<MediaPlayer>>();

        DbManager.insertDefaultData();
        settings = Settings.listAll(Settings.class).get(0);

        //PLAYERS SETTINGS
        bongoPlay  = findViewById(R.id.bongoPlay);
        DKPlayLeft = findViewById(R.id.DKPlay_Left);
        DKPlayRight = findViewById(R.id.DKPlay_Right);
        catUp = findViewById(R.id.catPlay_Up);
        catDown = findViewById(R.id.catPlay_Down);

        //FOR BONGO-CAT, SO HE ONLY PLAYS WHEN THE PAWS ARE ON THE LINE
        timer = new Timer();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                catDown.setVisibility(View.INVISIBLE);
                catUp.setVisibility(View.VISIBLE);
                return true;
            }
        });

        if(settings.getSelectedPlayer().equals("Bongo")) {
            DKPlayLeft.setVisibility(View.INVISIBLE);
            DKPlayRight.setVisibility(View.INVISIBLE);
            catDown.setVisibility(View.INVISIBLE);
            catUp.setVisibility(View.INVISIBLE);
            bongoPlay.setVisibility(View.VISIBLE);
            bongoIsChosen = true;
            catIsChosen = false;
        } else if(settings.getSelectedPlayer().equals("DK")) {
            bongoPlay.setVisibility(View.INVISIBLE);
            DKPlayLeft.setVisibility(View.VISIBLE);
            DKPlayRight.setVisibility(View.INVISIBLE);
            catDown.setVisibility(View.INVISIBLE);
            catUp.setVisibility(View.INVISIBLE);
            bongoIsChosen = false;
            catIsChosen = false;
        } else if(settings.getSelectedPlayer().equals("Cat")) {
            bongoPlay.setVisibility(View.INVISIBLE);
            DKPlayLeft.setVisibility(View.INVISIBLE);
            DKPlayRight.setVisibility(View.INVISIBLE);
            catDown.setVisibility(View.INVISIBLE);
            catUp.setVisibility(View.VISIBLE);
            bongoIsChosen = false;
            catIsChosen = true;
        }

        //User selected "Random", adding his selected sounds to list "bongos"
        if(settings.getSelectedSound().equals("Random")) {
            List<SettingsOfRandom> settingsOfRandom = SettingsOfRandom.listAll(SettingsOfRandom.class);
            for (SettingsOfRandom setting : settingsOfRandom) {
                if (setting.isSelected()) {
                    switch (setting.getSoundName()) {
                        case "Bongo1":
                            bongos.add(bongoOne);
                            break;
                        case "Bongo2":
                            bongos.add(bongoTwo);
                            break;
                        case "Bongo3":
                            bongos.add(bongoThree);
                            break;
                        case "Bongo4":
                            bongos.add(bongoFour);
                            break;
                    }
                }
            }
        }
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
                } else if(settings.getSelectedSound().equals("Random")){
                    //Play a random sound of ones in list "bongos"
                    int max = bongos.size() - 1;
                    int min = 0;
                    int random = (int)(Math.random() * ((max - min) + 1)) + min;
                    bongos.get(random).get(bongoCounter).start();
                }

                if(bongoIsChosen == false){ //User selected Donkey Kong or Bongo Cat in settings
                    if(catIsChosen == false) { //User selected Donkey Kong in settings
                        DKPlayLeft.setVisibility(DKPlayLeft.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
                        DKPlayRight.setVisibility(DKPlayRight.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
                    } else if(catIsChosen == true){ //User selected Bongo Cat in settings
                        catUp.setVisibility(View.INVISIBLE);
                        catDown.setVisibility(View.VISIBLE);
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(0);
                            }
                        }, 111);
                    }
                }

                //update bongoCounter
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
