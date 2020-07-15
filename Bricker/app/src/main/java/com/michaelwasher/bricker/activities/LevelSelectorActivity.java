package com.michaelwasher.bricker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.Resources.LevelStartInformation;
import com.michaelwasher.bricker.views.LevelSelector;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectorActivity extends AppCompatActivity {

    // Taken from https://gist.github.com/alphamu/7301820
    public static ArrayList<LevelSelector> getLevelSelectors(ViewGroup root) {
        ArrayList<LevelSelector> views = new ArrayList<LevelSelector>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getLevelSelectors((ViewGroup) child));
            }
            if (child != null && child instanceof LevelSelector) {
                views.add((LevelSelector)child);
            }
        }
        return views;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);

        // Get all Level Selectors
        final ArrayList<LevelSelector> selectorList = this.getLevelSelectors((ViewGroup)this.findViewById(R.id.levelSelectorGrid));
        for (LevelSelector selector : selectorList){
            if(selector.getLocked()){
                    continue;
            }
            selector.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    startGameEasy();
                }
            });
        }
    }

    protected void startGameEasy() {
        LevelStartInformation lsi = new LevelStartInformation(50, 10,
                3, 1, R.layout.level_one);
        startGame(lsi);

    }

    protected void startGame(LevelStartInformation lsi) {
        Intent intent = new Intent(this, GameActivity.class);
        //Create information for start

        intent.putExtra("LevelStartInformation", lsi);
        startActivity(intent);
    }

}
