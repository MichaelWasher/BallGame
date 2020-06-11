package com.example.ballgame.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.example.ballgame.R;

public class GreenWhiteBrick extends Brick {
    // Constructor
    public GreenWhiteBrick(Context context) {
        this(context, null);
    }
    public GreenWhiteBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_green_white_brick;
    }
}
