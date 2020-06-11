package com.michaelwasher.bricker.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.michaelwasher.bricker.R;

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
        this.setBackground(getSprite());
        if (starLevel != 4)
            this.setText(String.valueOf(levelNumber));
        else
            this.setText("");
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
            case 4:
                sprite = context.getDrawable(R.drawable.ic_level_selector_locked);
                break;
            default:
                sprite = context.getDrawable(R.drawable.ic_level_selector_zero_star);
                break;
        }
        return sprite;
    }

}
