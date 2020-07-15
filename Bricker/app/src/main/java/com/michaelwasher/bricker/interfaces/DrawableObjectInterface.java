package com.michaelwasher.bricker.interfaces;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public interface DrawableObjectInterface {
    //Drawable Aspects
    void draw(Canvas c, Paint p);

    Rect getDrawableBounds();

    //Relative Move
    void repositionRelative(Point p);

    void repositionRelative(int x, int y);

    //Absolute Move
    void repositionAbsolute(Point p);

    void repositionAbsolute(int x, int y);

}