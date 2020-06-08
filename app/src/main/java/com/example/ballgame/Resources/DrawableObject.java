package com.example.ballgame.Resources;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.ballgame.interfaces.DrawableObjectInterface;

public abstract class DrawableObject implements DrawableObjectInterface {

    protected Point currentPosition;

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
