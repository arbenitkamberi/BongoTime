package com.ubs.bongotime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View v){
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);
    }

    public void settings(View v){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void howToPlay(View v){
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
}
