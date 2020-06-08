package com.example.ballgame.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.ballgame.R;

public class MainActivity extends AppCompatActivity {

    boolean HIDE_ACTION_BAR = true;
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

    protected void openLevelSelect()
    {
        Intent i = new Intent(this, LevelSelector.class);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
