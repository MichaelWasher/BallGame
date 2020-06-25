package com.michaelwasher.bricker.Resources;

import android.graphics.Canvas;
import android.util.Log;
import android.widget.TextView;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.activities.GameActivity;

public class GameLoop extends Thread {
    public static Canvas canvas;

    private int FPS = 30;
    private double avgFPS;
    GamePlay game;
    GameActivity gameActivity;
    boolean running;

    public GameLoop(GameActivity gameActivity, GamePlay game) {
        super();
        this.game = game;
        this.gameActivity = gameActivity;
    }
    //Game Loop
    protected String getScoreText() {

        String scorePlaceholder = this.gameActivity.getResources().getString(R.string.game_state_text_format);
        return String.format(scorePlaceholder, game.getLevel(), game.getScore(), game.getLives());
    }
    protected void updateScoreTextView(){
        // If no values have been updated then no need to publish
        if(!game.getUpdated())
            return;

        final TextView scoreTextView = this.gameActivity.findViewById(R.id.scoreTextView);
        final String scoreText = this.getScoreText();
        this.gameActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(scoreTextView != null && scoreText != null) {
                    scoreTextView.setText(scoreText);
                    scoreTextView.invalidate();
                }
            }
        });

    }

    @Override
    public void run() {
        long startTime, timeMillis, waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                //Perform update and invalidate
                this.game.onUpdate();
                this.updateScoreTextView();
                if(running) {
                    this.game.invalidate();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

            try {
                this.sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == FPS) {
                avgFPS = 100 / ((totalTime / frameCount) / 1_000_000);
                frameCount = 0;
                totalTime = 0;
                Log.i("Average FPS:", String.valueOf(avgFPS));
            }
        }
    }

    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

}
