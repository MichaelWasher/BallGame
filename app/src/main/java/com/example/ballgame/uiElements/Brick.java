package com.example.ballgame.uiElements;

//Import Statements
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.V2;

public class Brick extends DrawableRectangle {

    protected int BRICK_TYPE;

    // Constructor
    public Brick(Context context, V2 corner, float width, float height) {
        super(context, corner, width, height);
        this.BRICK_TYPE = R.drawable.ic_brown_white_brick;
    }
    public Brick(V2 corner, float width, float height) {
        this(MyApplication.getAppContext(), corner, width, height);
    }
    private void drawSprite(Canvas c){
        Drawable brickSprite = context.getDrawable(BRICK_TYPE);
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