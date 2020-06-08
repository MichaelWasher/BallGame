package com.example.ballgame.Resources;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.WindowManager;

import com.example.ballgame.activities.EndGame;

import java.util.ArrayList;

public class GamePlay extends DrawingView {
    //Class-Scope Variables
    //Starting Values
    protected int startSpeed;
    protected int currentSpeed;
    protected int collisionAccelerator;
    protected int platformLength;
    protected int brickPushTime;
    protected int numberOfLivesLeft;
    protected int numberOfBrickRows;
    protected float EPSILON = 0.01f;
    //Logic Control Variables
    protected boolean gameStarted = false;
    protected float windowWidth;
    protected float windowHeight;
    protected int score = 0;
    protected int scoreMultiplier = 10;
    //BrickStarting Points
    float startX = 0;
    float startY = 50;
    //Displayable Objects
    ArrayList<RowOfBricks> rowsOfBricks;
    Ball pinBall;
    Platform userPlatform;
    //COLORS
    int BRICK_COLOR = Color.BLACK;
    int BALL_COLOR = Color.BLUE;
    int PLATFORM_COLOR = Color.RED;
    int FONT_COLOR = Color.BLACK;
    int BACKGROUND_COLOR = Color.CYAN;
    //BRICKS
    int NUM_OF_BRICKS_PER_ROW = 10;
    float BRICK_HEIGHT = 100;

    //PinBall
    float PINBALL_RADIUS = 10;
    float PINBALL_PADDING = 30;

    //Platform
    float PLATFORM_WIDTH = 250;
    float PLATFORM_HEIGHT = 30;
    float PLATFORM_PADDING_BOTTOM = 400;

    //Text on Page
    float TEXT_PADDING = 10;
    int FONT_SIZE = 40;
    //String for Text
    String SCORE_TEXT = "SCORE: %d";
    String LIVES_TEXT = "Lives: %s";


    public GamePlay(Context context, LevelStartInformation startInfo) {
        super(context);
        //Initialize all Class-Scope values
        setStartInformation(startInfo, context);
        rowsOfBricks = new ArrayList<RowOfBricks>();
        createLevel();
        startGame();
    }


    //Game Loop

    protected void onDraw(Canvas canvas) {

        //ColorBackground
        paint.setColor(BACKGROUND_COLOR);
        canvas.drawPaint(paint);

        //Display Lives and Score
        paint.setColor(FONT_COLOR);
        paint.setTextSize(FONT_SIZE);
        paint.setTypeface(Typeface.SANS_SERIF);

        canvas.drawText(String.format(SCORE_TEXT, score), TEXT_PADDING, FONT_SIZE, paint);
        canvas.drawText(String.format(LIVES_TEXT, numberOfLivesLeft), (windowWidth / 2) + TEXT_PADDING, FONT_SIZE, paint);

        paint.setColor(BRICK_COLOR);

        //Draw all rows of bricks
        for (RowOfBricks rob : rowsOfBricks) {
            rob.draw(canvas, paint);
        }
        //Draw Platform
        paint.setColor(PLATFORM_COLOR);
        userPlatform.draw(canvas, paint);
        //Draw ping ball
        paint.setColor(BALL_COLOR);
        pinBall.draw(canvas, paint);
        //Update Game
        if (gameStarted)
            onUpdate();
        //Queue Redraw
        invalidate();

    }

    protected void onUpdate() {
        //Move the Platform (using sensor)
        //movePlatform();
        //Check for Collision with Bricks
        checkBrickCollision();
        checkBoundaryCollision();
        checkPlatformCollision();
        //Move Ball
        moveBall();
    }


    //Game Logic

    protected void startGame() {
        pinBall.direction = new V2(currentSpeed, currentSpeed);
        pinBall.move();
        gameStarted = true;
    }

    protected void endGame() {
        Intent i = new Intent(getContext(), EndGame.class);
        i.putExtra("FinalScore", score);
        getContext().startActivity(i);
    }

    //Game Progress
    protected void lossOfLife() {
        //player has died
        numberOfLivesLeft--;
        if (numberOfLivesLeft > 0)
            resetGame();
        else
            endGame();
    }


    //Game Logic and Management
    protected void resetGame() {
        //Reset all bricks, reset ball, reset platform.
        rowsOfBricks = new ArrayList<RowOfBricks>();
        createLevel();
        startGame();
    }


    //Collision Detection
    protected void checkPlatformCollision() {
        //Bounce off Platform
        userPlatform.reflectBall(pinBall.getCentre(), pinBall.direction, pinBall.getRadius());
    }

    protected void checkBoundaryCollision() {
        // Bounce off the walls
        if (pinBall.getCentre().x + pinBall.getRadius() > windowWidth || pinBall.getCentre().x - pinBall.getRadius() < 0)
            pinBall.direction.x = -pinBall.direction.x;

        if (pinBall.getCentre().y - pinBall.getRadius() < 0)
            pinBall.direction.y = -pinBall.direction.y;

        //Check if Played has missed the ball
        if (pinBall.getCentre().y + pinBall.getRadius() > getHeight()) {
            lossOfLife();
        }
    }

    protected boolean checkBrickCollision() {
        //Check for any collisons with bricks
        for (RowOfBricks rob : rowsOfBricks) {
            for (int i = 0; i < rob.listOfBricks.size(); i++) {
                //Collision Occurred, reflect and remove brick
                Brick b = rob.listOfBricks.get(i);
                if (rob.listOfBricks.get(i).intersectBall(pinBall.getCentre(), pinBall.getRadius())) {
                    //If pinball is higher than lowest point on brick
                    if (pinBall.getCentre().y - pinBall.getRadius() < b.getStartPoint().y + b.getHeight() ||
                            pinBall.getCentre().y + pinBall.getRadius() > b.getStartPoint().y) {
                        pinBall.direction.y = -pinBall.direction.y;
                    } else {
                        pinBall.direction.x = -pinBall.direction.x;
                    }

                    //Remove Brick and increase players score
                    rob.removeBrick(b);
                    score += scoreMultiplier;
                    return true;
                }
            }
        }
        return false;
    }

    //Movement
    protected void moveBall() {
        // Move and Display Pinball
        pinBall.move();

    }

    protected void movePlatform(boolean moveRight) {
        int currentStep = currentSpeed;
        //Move Left
        if (!moveRight)
            currentStep *= -1;
        if ((userPlatform.getStartPoint().x + userPlatform.getWidth() + currentStep) > windowWidth ||
                userPlatform.getStartPoint().x + currentStep < 0) {
            //Move back if going off screen
            currentStep = 0;
        }

        userPlatform.repositionRelative(currentStep, 0);
    }


    //Initial Design
    private void setStartInformation(LevelStartInformation startInfo, Context context) {
        //Set Starting Values from LevelStartInfo
        numberOfBrickRows = startInfo.startNumberOfBrickRows;
        startSpeed = currentSpeed = startInfo.startSpeed;
        collisionAccelerator = startInfo.collisionAccelerator;
        platformLength = startInfo.platformLength;
        brickPushTime = startInfo.brickPushTime;
        numberOfLivesLeft = startInfo.startNumberOfLives;


        //Getting Display Size
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.windowWidth = size.x;
        this.windowHeight = size.y;
    }


    private void createLevel() {
        //Create N amount of Brick Rows
        for (int i = 0; i < numberOfBrickRows; i++) {
            RowOfBricks rob = new RowOfBricks(NUM_OF_BRICKS_PER_ROW, new V2(startX, (startY + BRICK_HEIGHT * i)), windowWidth, BRICK_HEIGHT);
            rowsOfBricks.add(rob);
        }

        //Create Platform
        userPlatform = new Platform(
                new V2((windowWidth / 2) - (PLATFORM_WIDTH / 2), windowHeight - PLATFORM_PADDING_BOTTOM
        ), PLATFORM_WIDTH, PLATFORM_HEIGHT);
        //Create PlayerBall
        pinBall = new Ball(new V2(userPlatform.getStartPoint().x + (userPlatform.getWidth() / 2),
                userPlatform.getStartPoint().y - PINBALL_RADIUS - PINBALL_PADDING), PINBALL_RADIUS);

    }
}
