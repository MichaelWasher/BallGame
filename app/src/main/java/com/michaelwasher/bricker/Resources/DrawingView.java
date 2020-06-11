package com.michaelwasher.bricker.Resources;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class DrawingView extends RelativeLayout {

    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    // Called when the View has focus
    @Override
    public void onWindowFocusChanged(boolean focused) {
        super.onWindowFocusChanged(focused);
    }
}