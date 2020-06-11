package com.michaelwasher.bricker.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.Resources.V2;
import com.michaelwasher.bricker.interfaces.Collider;

public class Ball extends DrawableObject implements Collider {

    public V2 direction;
    private Paint paint;
    V2 center;
    ////////////////// PUBLIC METHODS ////////////////////////////////////

    public Ball(Context context, AttributeSet attr){
        super(context, attr);
        Log.d("Ball Created", "A Ball has been created.");
        // Setup Paint
        this.center = new V2(0,0);
        this.setBackground(context.getResources().getDrawable(R.drawable.ic_platform, null));

    }

    // Constructor
    public Ball(Context context) {
        this(context, null);
    }

    // Reflection of a ball against the circle - updates 'direction'
    @Override public void reflectBall(V2 centre, V2 direction, float radius) {
        // Build M.x here
        V2 m = this.getCentre();     // The middle
        float s = this.getWidth() / 2;   // The radius

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
        return (V2.subtract(centre, this.getCentre()).lengthSquared() < (this.getRadius() + radius)
                * (this.getRadius() + radius));
    }

    @Override
    public void repositionRelative(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    public void move()
    {
        repositionRelative((int)direction.x, (int)direction.y);
    }
    public V2 getCentre()
    {
        center.x = this.getX() + (this.getWidth() / 2);
        center.y = this.getY() + (this.getHeight() /2);
        return center;
    }
    public float getRadius()
    {
        return this.getWidth() / 2;
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Force Width / height lock
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }
}