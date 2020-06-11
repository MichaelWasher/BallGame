package com.michaelwasher.bricker.Resources;

import java.io.Serializable;

@SuppressWarnings("serial") //with this annotation we are going to hide compiler warning
public class LevelStartInformation implements Serializable {
    public int startSpeed;
    public int collisionAccelerator;
    public int startNumberOfLives;

    public LevelStartInformation(int startSpeed, int collisionAccelerator, int startNumberOfLives) {
        this.startSpeed = startSpeed;
        this.collisionAccelerator = collisionAccelerator;
        this.startNumberOfLives = startNumberOfLives;
    }

}