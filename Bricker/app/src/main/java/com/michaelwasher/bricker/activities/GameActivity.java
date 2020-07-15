package com.michaelwasher.bricker.activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.michaelwasher.bricker.MyApplication;
import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.Resources.GamePlay;
import com.michaelwasher.bricker.Resources.LevelStartInformation;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    GamePlay mainGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupSensor();
        //Gather Level Information
        Intent i = getIntent();
        LevelStartInformation lsi = (LevelStartInformation) i.getSerializableExtra("LevelStartInformation");
        //Display Game Play
        this.mainGame = (GamePlay) findViewById(R.id.gameWindow);
        this.mainGame.startGame(lsi);

        //Keep display awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    public void endGame() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MyApplication.getAppContext(), EndGameActivity.class);
                i.putExtra("FinalScore", 10);
                MyApplication.getAppContext().startActivity(i);
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.i("key pressed", "GO Left");
            this.mainGame.movePlatform(-40);
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.i("key pressed", "GO Right");
            this.mainGame.movePlatform(40);
        }
        return super.dispatchKeyEvent(event);
    }

    public void setupSensor() {

        // Deploy the Accelerometer
        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener accelerometerSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // More code goes here
                float yRotationValue = sensorEvent.values[0] * -1;
                mainGame.movement = (int) yRotationValue;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
                Log.i("AccuracyChanges", "Accel Changed");
            }
        };

        // Register the listener
        sensorManager.registerListener(accelerometerSensorListener,
                accelSensor, SensorManager.SENSOR_DELAY_GAME);

    }
}
