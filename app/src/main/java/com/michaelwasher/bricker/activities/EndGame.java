package com.michaelwasher.bricker.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.michaelwasher.bricker.R;

import androidx.appcompat.app.AppCompatActivity;

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
