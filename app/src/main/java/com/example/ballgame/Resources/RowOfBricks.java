package com.example.ballgame.Resources;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.ballgame.uiElements.Brick;

import java.util.ArrayList;

public class RowOfBricks {
	//Class-Scope Variables
	protected int numberOfBricks;
	protected float brickWidth;
	protected float brickHeight;
	protected float rowWidth;
	protected ArrayList<Brick> listOfBricks;
	V2 topCorner;

	public RowOfBricks(int numberOfBricks, V2 topCorner, float width, float height) {
		//Create row of bricks
		listOfBricks = new ArrayList<Brick>();

		this.numberOfBricks = numberOfBricks;
		this.topCorner = topCorner;
		this.rowWidth = width;
		this.brickHeight = height;
		//Calculate brick width
		this.brickWidth = rowWidth / numberOfBricks;
		populateBrickRow(this.listOfBricks);
	}

	public boolean removeBrick(int index) {
		if(listOfBricks.remove(index) != null)
				return true;
		return false;
	}

	public boolean removeBrick(Brick b) {
		if(listOfBricks.remove(b))
			return true;
		return false;
	}

	private Brick createBrick(V2 start, float width, float height) {
		return BrickBuilder.newRandomBrick(new V2(start.x, start.y), width, height);
	}

	private void populateBrickRow(ArrayList<Brick> brickRow) {
		//Set location pointer
		V2 currentLocation = new V2(topCorner.x, topCorner.y);
		//Iterate the number of bricks, creating brick and adding to list
		for (int i = 0; i < numberOfBricks; i++) {
			brickRow.add(createBrick(currentLocation, brickWidth, brickHeight));
			//Move Pointer Along along
			currentLocation.x += brickWidth;
		}
	}

	public void draw(Canvas c, Paint paint) {
		for (Brick b : listOfBricks) {
			b.draw(c, paint);
		}
	}

}
