package com.michaelwasher.bricker.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.michaelwasher.bricker.R;

public class GreenBlackBrick extends Brick {
    // Constructor
    public GreenBlackBrick(Context context) {
        this(context, null);
    }

    public GreenBlackBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_green_black_brick;
        this.setBackground(context.getResources().getDrawable(this.BRICK_TYPE, null));

    }
}
