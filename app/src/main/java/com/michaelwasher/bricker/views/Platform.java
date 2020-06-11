package com.michaelwasher.bricker.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.Resources.V2;

public class Platform extends DrawableRectangle {

    protected float boarderWidth = 5;
    Context context;

    // Constructors
    public Platform(Context context) {
        this(context, null);
    }
    public Platform(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("Platform Created", "A platform has been created.");

//        onUpdate();

        this.context = context;
        this.setBackground(context.getResources().getDrawable(R.drawable.ic_platform, null));
    }

//    @Override
//    public boolean onKeyDown (int keyCode,
//                               KeyEvent event){
//        Log.i("KeyPRessed", "KeyPressed");
//        Log.i("key pressed", String.valueOf(event.getKeyCode()));
//        if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN){
//            Log.i("key pressed", "GO Left");
//            this.movement = -10;
//        }else if(event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN){
//            Log.i("key pressed", "GO Right");
//        }
//        return super.dispatchKeyEvent(event);
//        return true;
//    }

    public void onUpdate() {
        //Wrapper Defintion
        V2 corner = new V2(this.getX(), this.getY());
        float width = this.getWidth();
        float height = this.getHeight();
        a = corner;
        b = V2.add(a, new V2(width, 0));
        c = V2.add(a, new V2(0, height));
        d = V2.add(a, new V2(width, height));
        pab = V2.subtract(a, c).normalize();
        pbd = V2.subtract(b, a).normalize();
        pdc = pab.negate();
        pca = pbd.negate();
    }

}
