package com.example.ballgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ballgame.R;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ballgame.Resources.LevelStartInformation;

public class LevelSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);


        //Setting Button On Click Methods
        final Button easyButton = (Button) findViewById(R.id.LevelSelectorEasy);
        easyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startGameEasy();
            }
        });

        final Button mediumButton = (Button) findViewById(R.id.LevelSelectorMedium);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startGameMedium();
            }
        });


        final Button hardButton = (Button) findViewById(R.id.LevelSelectorHard);
        hardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                startGameHard();
            }
        });



    }

    protected void startGameEasy()
    {
        LevelStartInformation lsi = new LevelStartInformation(2,50,10,10,10,3);
        startGame(lsi);

    }
    protected void startGameMedium()
    {
        LevelStartInformation lsi = new LevelStartInformation(2,10,10,10,10,3);
        startGame(lsi);

    }
    protected void startGameHard() {
        LevelStartInformation lsi = new LevelStartInformation(2,10,10,10,10,3);
        startGame(lsi);
    }

    protected void startGame(LevelStartInformation lsi)
    {
        Intent intent = new Intent(this, Game.class);
        //Create information for start

        intent.putExtra("LevelStartInformation", lsi);
        startActivity(intent);
    }

}
