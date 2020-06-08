package com.example.ballgame.uiElements;

import android.content.Context;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.V2;

public class GreenBlackBrick extends Brick {
    // Constructor
    public GreenBlackBrick(Context context, V2 corner, float width, float height) {
        super(context, corner, width, height);
        BRICK_TYPE = R.drawable.ic_green_black_brick;
    }
    public GreenBlackBrick(V2 corner, float width, float height) {
        this(MyApplication.getAppContext(), corner, width, height);
    }
}
