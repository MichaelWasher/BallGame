package com.example.ballgame.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.example.ballgame.R;

public class BrownBlackBrick extends Brick {
    // Constructor
    public BrownBlackBrick(Context context) {
        this(context, null);
    }
    public BrownBlackBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_brown_black_brick;
    }

}
