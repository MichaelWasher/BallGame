package com.example.ballgame.Resources;

import java.io.Serializable;

@SuppressWarnings("serial") //with this annotation we are going to hide compiler warning
public class LevelStartInformation implements Serializable
{
	public int startNumberOfBrickRows;
	public int startSpeed;
	public int collisionAccelerator;
	public int platformLength;
	public int brickPushTime;
	public int startNumberOfLives;

    public LevelStartInformation(int startNumberOfBrickRows, int startSpeed, int collisionAccelerator, int platformLength, int brickPushTime, int startNumberOfLives)
    {
        this.startNumberOfBrickRows = startNumberOfBrickRows;
        this.startSpeed = startSpeed;
        this.collisionAccelerator = collisionAccelerator;
        this.platformLength = platformLength;
        this.brickPushTime = brickPushTime;
        this.startNumberOfLives = startNumberOfLives;
    }

}