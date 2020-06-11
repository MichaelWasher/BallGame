package com.michaelwasher.bricker.views.bricks;

import android.content.Context;
import android.util.AttributeSet;

import com.michaelwasher.bricker.R;

public class BrownWhiteBrick extends Brick {
    public BrownWhiteBrick(Context context) {
        this(context, null);
    }
    public BrownWhiteBrick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_brown_white_brick_no_exclam;
    }
}
