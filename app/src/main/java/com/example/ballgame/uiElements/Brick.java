package com.example.ballgame.uiElements;

//Import Statements
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.DrawingView;
import com.example.ballgame.Resources.V2;

public class Brick extends DrawableRectangle {

    protected int BRICK_TYPE;
    // Constructor
    public Brick(Context context) {
        this(context, null);
    }

    public Brick(Context context, AttributeSet attr) {
        super(context, attr);
        this.BRICK_TYPE = R.drawable.ic_brown_white_brick;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(this.context != null){
            Drawable brickSprite = this.context.getDrawable(BRICK_TYPE);
            brickSprite.setBounds(0,0, this.getWidth(), this.getHeight());
            brickSprite.draw(canvas);
        }
    }
}