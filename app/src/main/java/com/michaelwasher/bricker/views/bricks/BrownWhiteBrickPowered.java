package com.michaelwasher.bricker.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.michaelwasher.bricker.R;

public class BrownWhiteBrickPowered extends Brick {
    public BrownWhiteBrickPowered(Context context) {
        this(context, null);
    }

    public BrownWhiteBrickPowered(Context context, AttributeSet attr) {
        super(context, attr);
        //TODO replace
        this.BRICK_TYPE = R.drawable.ic_brown_white_brick_powered;
        this.setBackground(context.getResources().getDrawable(this.BRICK_TYPE, null));
    }
}