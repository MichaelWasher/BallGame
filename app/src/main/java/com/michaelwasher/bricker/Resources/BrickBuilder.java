package com.michaelwasher.bricker.Resources;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.michaelwasher.bricker.MyApplication;
import com.michaelwasher.bricker.views.bricks.Brick;
import com.michaelwasher.bricker.views.bricks.BrownWhiteBrick;

import java.util.ArrayList;
import java.util.Random;

public class BrickBuilder {
    static Random rnd = new Random();

    public static Brick newRandomBrick(V2 corner, float width, float height) {
        //Debug with Test Brick
        // TODO Clean up and remove context refernce
        Brick brick;
        Context context = MyApplication.getAppContext();
        LayoutParams layoutParams = new LayoutParams((int) width, (int) height);
        brick = new BrownWhiteBrick(context);
        brick.setX(corner.x);
        brick.setY(corner.y);
        brick.setLayoutParams(layoutParams);
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

    public static ArrayList<Brick> GatherBricks(RelativeLayout layout) {
        ArrayList<Brick> allBricks = new ArrayList<Brick>();
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layout.getChildAt(i);
            if (v instanceof Brick) {
                allBricks.add((Brick) v);
            }
        }
        return allBricks;
    }

}
