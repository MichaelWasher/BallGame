package com.example.ballgame.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.example.ballgame.R;

public class BrownWhiteBrick extends Brick {
    public BrownWhiteBrick(Context context) {
        this(context, null);
    }
    public BrownWhiteBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_brown_white_brick_no_exclam;
    }
}
