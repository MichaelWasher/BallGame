package com.example.ballgame.Resources;

//Import Statements
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.example.ballgame.MyApplication;
import com.example.ballgame.interfaces.Collider;
import com.example.ballgame.R;

public class Brick extends DrawableRectangle {

    // Constructor
    public Brick(Context context, V2 corner, float width, float height) {
        super(context, corner, width, height);
    }
    public Brick(V2 corner, float width, float height) {
        this(MyApplication.getAppContext(), corner, width, height);
    }
    private void drawSprite(Canvas c){
        Drawable brickSprite = context.getDrawable(R.drawable.ic_brown_brick);
        brickSprite.setBounds((int)this.a.x, (int)this.a.y, (int)this.d.x, (int)this.d.y);
        brickSprite.draw(c);
    }
    private void drawPaint(Canvas c, Paint p){
        int originalColor = p.getColor();
        c.drawRect(a.x, a.y, d.x, d.y, p);
        p.setColor(Color.WHITE);
                c.drawRect(a.x + boarderWidth, a.y + boarderWidth, d.x - boarderWidth, d.y - boarderWidth, p );
        p.setColor(originalColor);
    }
    // Draw the rectangle
    @Override
    public void draw(Canvas c, Paint p) {
        if(this.context != null){
            drawSprite(c);
        }else{
            drawPaint(c, p);
        }
    }
}