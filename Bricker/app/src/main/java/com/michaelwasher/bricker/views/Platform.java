package com.michaelwasher.bricker.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.Resources.V2;

public class Platform extends DrawableRectangle {
    Context context;

    // Constructors
    public Platform(Context context) {
        this(context, null);
    }

    public Platform(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("Platform Created", "A platform has been created.");
        this.context = context;
        this.setBackground(context.getResources().getDrawable(R.drawable.ic_platform, null));
    }
}
