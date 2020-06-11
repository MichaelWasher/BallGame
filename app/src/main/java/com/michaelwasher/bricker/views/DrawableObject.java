package com.michaelwasher.bricker.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.michaelwasher.bricker.interfaces.DrawableObjectInterface;

import androidx.annotation.Nullable;

public abstract class DrawableObject extends View implements DrawableObjectInterface {

    protected Point currentPosition;
    public DrawableObject(Context context) {
        super(context);
    }

    public DrawableObject(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas c, Paint p) {

    }

    @Override
    public Rect getDrawableBounds() {
        return null;
    }

    @Override
    public void repositionRelative(Point p) {
        repositionRelative(p.x, p.y);
    }

    @Override
    public void repositionRelative(int x, int y) {
        currentPosition.x += x;
        currentPosition.y += y;
    }

    @Override
    public void repositionAbsolute(Point p) {
        repositionAbsolute(p.x, p.y);
    }

    @Override
    public void repositionAbsolute(int x, int y) {
        currentPosition.x = x;
        currentPosition.y = y;
    }
}
