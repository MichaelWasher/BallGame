package com.example.ballgame.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;

import com.example.ballgame.MyApplication;
import com.example.ballgame.R;
import com.example.ballgame.Resources.V2;

public class LevelSelector extends AppCompatButton {
    //circle and text colors
    private int starLevel, levelNumber;
    private Context context;
    private Paint paint;

    public LevelSelector(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        this.paint = new Paint();

        // Populate class based on Attributes
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LevelSelector, 0, 0);
        try {
            this.starLevel = a.getInteger(R.styleable.LevelSelector_starLevel, 0);
            this.levelNumber = a.getInteger(R.styleable.LevelSelector_levelNumber, 0);
        } finally {
            a.recycle();
        }
    }
    protected Point getLocation()
    {
        int[] tmpLocation = new int[2];
        this.getLocationOnScreen(tmpLocation);
        return new Point(tmpLocation[0], tmpLocation[1]);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View
        int width = this.getMeasuredWidth();
        int height = this.getMeasuredWidth();
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;
        Point location = getLocation();
        Drawable sprite = getSprite();

        sprite.setBounds(0, 0,
                width, height);
        sprite.draw(canvas);

        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
//        canvas.drawText(String.valueOf(levelNumber), viewWidthHalf, viewHeightHalf, paint);
    }

    private Drawable getSprite(){
        Drawable sprite;
        switch(this.starLevel)
        {
            case 1:
                sprite = context.getDrawable(R.drawable.ic_level_selector_one_star);
                break;
            case 2:
                sprite = context.getDrawable(R.drawable.ic_level_selector_two_star);
                break;
            case 3:
                sprite = context.getDrawable(R.drawable.ic_level_selector_three_star);
                break;
            default:
                sprite = context.getDrawable(R.drawable.ic_level_selector_zero_star);
                break;
        }
        return sprite;
    }

}
