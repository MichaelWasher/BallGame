package com.example.ballgame.interfaces;


import android.graphics.Canvas;
import android.graphics.Paint;
import com.example.ballgame.Resources.V2;

public interface Collider {
    public void reflectBall(V2 centre, V2 direction, float radius);
    public boolean intersectBall(V2 centre, float radius);
    public void draw(Canvas c, Paint p);
}