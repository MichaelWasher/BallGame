package com.example.ballgame.activities;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ballgame.R;

public class EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        //Add Score to display
        int score = getIntent().getIntExtra("FinalScore", 0);
        final TextView scoreTextView = (TextView) findViewById(R.id.FinalScoreTextView);
        scoreTextView.setText(String.format(scoreTextView.getText() + "%d", score));


    }

}
