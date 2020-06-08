package com.example.ballgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.ballgame.Resources.GamePlay;
import com.example.ballgame.Resources.LevelStartInformation;

public class Game extends AppCompatActivity {

    GamePlay gp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gather Level Information
        Intent i = getIntent();
        LevelStartInformation lsi = (LevelStartInformation)i.getSerializableExtra("LevelStartInformation");
        //Display Game Play
        gp = new GamePlay(this, lsi);
        setContentView(gp);
        //Keep display awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }





}
