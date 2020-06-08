package com.example.ballgame.Resources;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ballgame.interfaces.Collider;

public class Ball extends DrawableObject implements Collider {
    private V2 m;      // The middle
    private float s;   // The radius
    public V2 direction;
    ////////////////// PUBLIC METHODS ////////////////////////////////////

    // Constructor;
    public Ball(V2 middle, float radius) {
        m = middle;
        s = radius;
    }

    // Reflection of a ball against the circle - updates 'direction'
    @Override public void reflectBall(V2 centre, V2 direction, float radius) {
        if (!intersectBall(centre, radius)) {
            float deltax, deltay, lensq, bb, cc, det, lambda;
            deltax = m.x - centre.x;
            deltay = m.y - centre.y;
            lensq = (direction.x * direction.x + direction.y * direction.y);
            bb = (direction.x * deltax + direction.y * deltay) / lensq;
            cc = (deltax * deltax + deltay * deltay - (s + radius) * (s + radius)) / lensq;
            det = bb * bb - cc;
            if (det >= 0) {
                lambda = bb - (float) Math.sqrt(det);
                if (lambda >= 0 && lambda < 1) {
                    V2 hit = V2.add(centre, V2.multiply(direction, lambda));
                    V2 normal = V2.subtract(hit, m).normalize();
                    V2 newdir = V2.subtract(direction, V2.multiply(V2.multiply(normal, V2.dot(direction, normal)), 2));
                    direction.x = newdir.x;
                    direction.y = newdir.y;
                }
            }
        }
    }

    // Does a ball intersect with the circle
    @Override public boolean intersectBall(V2 centre, float radius) {
        return (V2.subtract(centre, m).lengthSquared() < (s + radius) * (s + radius));
    }

    @Override
    public void repositionRelative(int x, int y) {
        m.x += x;
        m.y += y;
    }
    public void move()
    {
        repositionRelative((int)direction.x, (int)direction.y);
    }
    public V2 getCentre()
    {
        return this.m;
    }
    public float getRadius()
    {
        return this.s;
    }
    @Override
    // Draw the circle
    public void draw(Canvas c, Paint p) {
        c.drawCircle(m.x, m.y, s, p);
    }
}