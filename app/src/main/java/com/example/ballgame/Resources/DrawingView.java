package com.example.ballgame.Resources;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class DrawingView extends View {
    protected Paint paint = new Paint();
    protected int drawNum = 0;

    public DrawingView(Context context) {
        super(context);

        // Set up the Paint object with some default settings
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextSize(50);
    }
    public DrawingView(Context context, AttributeSet attr) {
        super(context, attr);
    }

        int x = 0;
    int y = 0;
    // Called every time the screen is redrawn
    @Override
    protected void onDraw(Canvas canvas) {

        // Queue a redrawing operation
        invalidate();
    }

    // Called when the View has focus
    @Override
    public void onWindowFocusChanged(boolean focused) {
        super.onWindowFocusChanged(focused);
    }
}