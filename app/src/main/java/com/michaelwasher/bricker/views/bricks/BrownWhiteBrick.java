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
        //TODO replace
        this.BRICK_TYPE = R.drawable.ic_group_20;
        this.setBackground(context.getResources().getDrawable(this.BRICK_TYPE, null));

    }
}
