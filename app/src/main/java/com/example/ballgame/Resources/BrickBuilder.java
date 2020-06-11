package com.example.ballgame.Resources;

import android.content.Context;
import android.widget.RelativeLayout;

import com.example.ballgame.MyApplication;
import com.example.ballgame.uiElements.Brick;
import com.example.ballgame.uiElements.BrownBlackBrick;
import com.example.ballgame.uiElements.BrownWhiteBrick;
import com.example.ballgame.uiElements.GreenBlackBrick;
import com.example.ballgame.uiElements.GreenWhiteBrick;

import java.util.Random;

public class BrickBuilder {
    static Random rnd = new Random();

    public static Brick newRandomBrick(V2 corner, float width, float height)
    {
        //Debug with Test Brick
        // TODO Clean up and remove context refernce
        Brick brick;
        Context context = MyApplication.getAppContext();
        brick = new BrownWhiteBrick(context);
        brick.setLayoutParams(new RelativeLayout.LayoutParams((int)width, (int)height));
        return brick;
//        return new BrownWhiteBrick(corner, width, height);

//        int randomNumber = rnd.nextInt( 100);
//
//        if(randomNumber < 10) {
//            return new GreenBlackBrick(corner, width, height);
//        }else if(randomNumber < 30) {
//            return new GreenWhiteBrick(corner, width, height);
//        }else if(randomNumber < 50) {
//            return new BrownBlackBrick(corner, width, height);
//        }else {
//            return new BrownWhiteBrick(corner, width, height);
//        }
    }
}
