package com.michaelwasher.bricker.Resources;

import android.content.Context;
import android.widget.RelativeLayout;

import com.michaelwasher.bricker.MyApplication;
import com.michaelwasher.bricker.views.bricks.Brick;
import com.michaelwasher.bricker.views.bricks.BrownWhiteBrick;

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
