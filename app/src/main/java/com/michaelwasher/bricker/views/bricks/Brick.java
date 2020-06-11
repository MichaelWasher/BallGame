package com.michaelwasher.bricker.views.bricks;

//Import Statements
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.views.DrawableRectangle;

public class Brick extends DrawableRectangle {

    protected int BRICK_TYPE;
    // Constructor
    public Brick(Context context) {
        this(context, null);
    }

    public Brick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_brown_white_brick;
        Log.d("Brick Created", "A brick has been created.");
        this.setBackground(context.getResources().getDrawable(this.BRICK_TYPE, null));
    }
}