package com.michaelwasher.bricker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.michaelwasher.bricker.R;

import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        //Add Score to display
        int score = getIntent().getIntExtra("FinalScore", 0);
        final TextView scoreTextView = (TextView) findViewById(R.id.FinalScoreTextView);
        scoreTextView.setText(String.format(scoreTextView.getText() + "%d", score));

        //Set onClick Listener
        final Button mainClick = (Button) findViewById(R.id.EndGameFullScreenListener);
        mainClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                openLevelSelect();
            }
        });
    }

    protected void openLevelSelect() {
        Intent i = new Intent(this, LevelSelectorActivity.class);
        startActivity(i);
    }
}
