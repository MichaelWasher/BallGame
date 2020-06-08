package com.example.ballgame.uiElements;

import android.content.Context;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.V2;

public class BrownBlackBrick extends Brick {
    // Constructor
    public BrownBlackBrick(Context context, V2 corner, float width, float height) {
        super(context, corner, width, height);
        BRICK_TYPE = R.drawable.ic_brown_black_brick;
    }
    public BrownBlackBrick(V2 corner, float width, float height) {
        this(MyApplication.getAppContext(), corner, width, height);
    }
}
