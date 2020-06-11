package com.example.ballgame.uiElements;

import android.content.Context;
import android.util.AttributeSet;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.V2;

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
