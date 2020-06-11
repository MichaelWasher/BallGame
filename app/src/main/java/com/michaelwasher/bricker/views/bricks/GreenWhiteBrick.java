package com.michaelwasher.bricker.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.michaelwasher.bricker.R;

public class GreenWhiteBrick extends Brick {
    // Constructor
    public GreenWhiteBrick(Context context) {
        this(context, null);
    }

    public GreenWhiteBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_green_white_brick;
        this.setBackground(context.getResources().getDrawable(this.BRICK_TYPE, null));

    }
}
