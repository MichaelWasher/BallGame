package com.michaelwasher.bricker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.michaelwasher.bricker.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set onClick Listener
        final Button mainClick = (Button) findViewById(R.id.WelcomeScreenOnClickListener);
        mainClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                openLevelSelect();
            }
        });

    }

    protected void openLevelSelect() {
        Intent i = new Intent(this, LevelSelector.class);
        startActivity(i);
    }
}
