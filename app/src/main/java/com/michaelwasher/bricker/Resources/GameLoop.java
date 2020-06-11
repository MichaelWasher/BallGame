package com.michaelwasher.bricker.Resources;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{
    public static Canvas canvas;

    private int FPS = 30;
    private double avgFPS;
    GamePlay game;

    boolean running;

    public GameLoop(GamePlay game)
    {
        super();
        this.game = game;
    }
    @Override
    public void run(){
        long startTime, timeMillis, waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;
            timeMillis = (System.nanoTime() - startTime) /1000000;
            waitTime = targetTime - timeMillis;

            try{
                //TODO  Perform update and invalidate!
                this.game.onUpdate();
                this.game.invalidate();
                //Might need to make thread safe?

            }catch(Exception e) {
                e.printStackTrace();
                throw e;
            }

            try{
                this.sleep(waitTime);
            }catch (Exception e){
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == FPS){
                avgFPS = 100 / ((totalTime / frameCount) / 1_000_000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avgFPS);
                Log.i("Average FPS:", String.valueOf(avgFPS));
            }
        }
    }
    public void setRunning(boolean isRunning)
    {
        this.running = isRunning;
    }

}
