package com.example.ballgame.activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ballgame.Resources.GamePlay;
import com.example.ballgame.Resources.LevelStartInformation;

public class Game extends AppCompatActivity {

    GamePlay gp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSensor();
        //Gather Level Information
        Intent i = getIntent();
        LevelStartInformation lsi = (LevelStartInformation)i.getSerializableExtra("LevelStartInformation");
        //Display Game Play
        gp = new GamePlay(this, lsi);
        setContentView(gp);
        //Keep display awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN){
            Log.i("key pressed", "GO Left");
            gp.movement = -10;
        }else if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN){
            Log.i("key pressed", "GO Right");
            gp.movement = 10;
        }
        return super.dispatchKeyEvent(event);
    }

    public void setupSensor()
    {

        // Deploy the Accelerometer
        SensorManager sensorManager =
                (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener accelerometerSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // More code goes here
                float yRotationValue = sensorEvent.values[0] * -1;
                gp.movement = (int)yRotationValue;
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
