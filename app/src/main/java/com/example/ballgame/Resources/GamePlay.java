package com.example.ballgame.Resources;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ballgame.R;
import com.example.ballgame.activities.EndGame;
import com.example.ballgame.views.Ball;
import com.example.ballgame.views.bricks.Brick;
import com.example.ballgame.views.Platform;

import java.util.ArrayList;

public class GamePlay extends DrawingView {
    //Class-Scope Variables
    public int movement = 0;
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
    float startY = 100;

    //Displayable Objects
    ArrayList<RowOfBricks> rowsOfBricks;
    Ball pinBall;
    Platform userPlatform;

    //BRICKS
    int NUM_OF_BRICKS_PER_ROW = 10;
    float BRICK_HEIGHT = 100;

    //PinBall
    float PINBALL_RADIUS = 10;
    float PINBALL_PADDING = 30;

    //String for Text
    String SCORE_TEXT = "Score: %d";
    String LIVES_TEXT = "Lives: %s";
    Context context;

    public GamePlay(Context context, LevelStartInformation startInfo) {
        super(context);
        this.context = context;
    }
    public GamePlay(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
    }

    public void startGame(LevelStartInformation startInfo)
    {
        //Initialize all Class-Scope values
        setStartInformation(startInfo, this.context);
        rowsOfBricks = new ArrayList<RowOfBricks>();
        createLevel();
        startGame();
    }

    //Game Loop
    protected String getScoreText(int newScore, int newLifeCount) {

        String scorePlaceholder = context.getResources().getString(R.string.game_state_text_format);
        return String.format(scorePlaceholder, newScore, newLifeCount);
    }

    protected void onDraw(Canvas canvas) {
        //Get Elements
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);


        //ColorBackground
        paint.setColor(getResources().getColor(
                R.color.backgroundColor,null));
        canvas.drawPaint(paint);

        paint.setColor(getResources().getColor(
            R.color.brickColor,null));

        //Draw all rows of bricks
        for (RowOfBricks rob : rowsOfBricks) {
            rob.draw(canvas, paint);
        }
        //Draw Platform
        paint.setColor(getResources().getColor(
                R.color.platformColor,null));
        userPlatform.draw(canvas);
        //Draw ping ball
        paint.setColor(getResources().getColor(
                R.color.ballColor,null));
        pinBall.draw(canvas, paint);
        //Update Game
        if (gameStarted)
            onUpdate();

        //Queue Redraw
        invalidate();
        if(scoreTextView != null) {
            //Display Lives and Score
            scoreTextView.setText(getScoreText(score,numberOfLivesLeft));
            scoreTextView.draw(canvas);
            scoreTextView.invalidate();
        }
        // TODO Get all 'actual' elemtns on the page and redraw
    }

    protected void checkMovePlatform(){
        Log.i("Movement: ", String.valueOf(this.movement));
        if(this.movement != 0) {
            movePlatform();
            this.movement = 0;
        }
    }

    protected void onUpdate() {
        //Move the Platform (using sensor)
        checkMovePlatform();

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

    protected void movePlatform() {

        int currentStep = currentSpeed;
        currentStep *= this.movement;

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
        userPlatform = new Platform(this.context);
        //Create PlayerBall
        pinBall = new Ball(this.context);

    }
}
