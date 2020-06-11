package com.example.ballgame.uiElements;

import android.content.Context;
import android.util.AttributeSet;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.V2;

public class GreenBlackBrick extends Brick {
    // Constructor
    public GreenBlackBrick(Context context) {
        this(context, null);
    }
    public GreenBlackBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_green_black_brick;
    }
}
