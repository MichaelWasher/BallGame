package com.michaelwasher.bricker.Resources;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.activities.EndGame;
import com.michaelwasher.bricker.views.Ball;
import com.michaelwasher.bricker.views.bricks.Brick;
import com.michaelwasher.bricker.views.Platform;

import java.util.ArrayList;

public class GamePlay extends DrawingView {
    //Class-Scope Variables
    public int movement = 0;
    //Starting Values
    protected int startSpeed;
    protected int currentSpeed;
    protected int collisionAccelerator;

    protected int numberOfLivesLeft;
    protected int numberOfBrickRows;
    protected float EPSILON = 0.01f;

    //Logic Control Variables
    protected boolean gameStarted = false;
    protected int score = 0;
    protected int scoreMultiplier = 10;

    //Displayable Objects
    ArrayList<Brick> allBricks;
    Ball pinBall;
    Platform userPlatform;

    //String for Text
    String SCORE_TEXT = "Score: %d";
    String LIVES_TEXT = "Lives: %s";
    Context context;

    GameLoop gameLoop;
    int levelLayout = R.layout.level_one;

    public GamePlay(Context context, AttributeSet attr) {
        super(context, attr);
        Log.d("GamePlay Created", "A GamePlay has been created.");
        this.context = context;
        gameLoop = new GameLoop(this);
    }


    public void startGame(LevelStartInformation startInfo)
    {
        //Initialize all Class-Scope values
        setStartInformation(startInfo, this.context);
        createLevel();
        startGame();
    }

    //Game Loop
    protected String getScoreText(int newScore, int newLifeCount) {

        String scorePlaceholder = context.getResources().getString(R.string.game_state_text_format);
        return String.format(scorePlaceholder, newScore, newLifeCount);
    }

    @Override
    public void invalidate(){
        //Queue Redraw
        super.invalidate();
        for(Brick brick : this.allBricks )
        {
            brick.invalidate();
        }
        pinBall.invalidate();
        userPlatform.invalidate();
    }

    protected void checkMovePlatform(){
        Log.i("Movement: ", String.valueOf(this.movement));
        if(this.movement != 0) {
            movePlatform(10);
            this.movement = 0;
        }
    }

    protected void onUpdate() {
        if(this.getWidth() <=0)
            return;
        //Move Ball
        moveBall();
        //Move the Platform (using sensor)
//        checkMovePlatform();
        // Requires for keeping a,b,c,d in sync
        if(this.userPlatform != null)
            userPlatform.onUpdate();
        //Check for Collision with Bricks
        checkBrickCollision();
        checkBoundaryCollision();
        checkPlatformCollision();

    }


    //Game Logic
    protected void startGame() {
        pinBall.direction = new V2(currentSpeed, currentSpeed);
        pinBall.move();
        gameStarted = true;
        // Separate Thread
        gameLoop.setRunning(true);
        gameLoop.start();
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
        Log.d("Life Lost", "A life was lost.");
//        if (numberOfLivesLeft > 0)
//            resetGame();
//        else
//            endGame();
    }


    //Game Logic and Management
    protected void resetGame() {
        //Reset all bricks, reset ball, reset platform.
        //TODO fix
//        this.allBricks
//        createLevel();
//        startGame();
    }


    //Collision Detection
    protected void checkPlatformCollision() {
        //Bounce off Platform
        userPlatform.reflectBall(pinBall.getCentre(), pinBall.direction, pinBall.getRadius());
    }

    protected void checkBoundaryCollision() {
        // Bounce off the walls
        if (pinBall.getCentre().x + pinBall.getRadius() > this.getWidth() || pinBall.getCentre().x - pinBall.getRadius() < 0)
            pinBall.direction.x = -pinBall.direction.x;

        // Bounce of the Roof
        if (pinBall.getCentre().y - pinBall.getRadius() < 0)
            pinBall.direction.y = -pinBall.direction.y;

        // TODO DEBUG Bounce off the ground
        if (pinBall.getCentre().y + pinBall.getRadius() > this.getHeight()) {
            pinBall.direction.y = -pinBall.direction.y;
            return;
        }

        //Check if Played has missed the ball
        if (pinBall.getCentre().y + pinBall.getRadius() > this.getHeight()) {
            Log.d("life loss values", "Pinball.Y: " + String.valueOf(pinBall.getCentre().y) +
                    "Pinball Radius: "  + String.valueOf(pinBall.getRadius()) + " height: " + String.valueOf(this.getHeight()));
            lossOfLife();
        }
    }

    protected boolean checkBrickCollision() {
        //Check for any collisons with bricks
        if(this.allBricks == null)
            return false;

        for (int i = 0; i < this.allBricks.size(); i++) {
            //Collision Occurred, reflect and remove brick
            Brick b = allBricks.get(i);
            if (b.intersectBall(pinBall.getCentre(), pinBall.getRadius())) {
                //If pinball is higher than lowest point on brick
                if (pinBall.getCentre().y - pinBall.getRadius() < b.getStartPoint().y + b.getHeight() ||
                        pinBall.getCentre().y + pinBall.getRadius() > b.getStartPoint().y) {
                    pinBall.direction.y = -pinBall.direction.y;
                } else {
                    pinBall.direction.x = -pinBall.direction.x;
                }

                //Remove Brick and increase players score
                allBricks.remove(i);
                b.setVisibility(View.INVISIBLE);
                score += scoreMultiplier;
                return true;
            }
        }
        return false;
    }

    //Movement
    protected void moveBall() {
        // Move and Display Pinball
        pinBall.move();

    }

    public void movePlatform(int movement) {

        //Check the walls
        if ((userPlatform.getStartPoint().x + userPlatform.getWidth() + movement) > this.getWidth() ||
                userPlatform.getStartPoint().x + movement < 0) {
//            Move back if going off screen
            movement = 0;
        }

        userPlatform.repositionRelative(movement, 0);
    }


    //Initial Design
    private void setStartInformation(LevelStartInformation startInfo, Context context) {
        //Set Starting Values from LevelStartInfo
//        numberOfBrickRows = startInfo.startNumberOfBrickRows;
        startSpeed = currentSpeed = startInfo.startSpeed;
        collisionAccelerator = startInfo.collisionAccelerator;
        numberOfLivesLeft = startInfo.startNumberOfLives;
    }


    private void createLevel() {
        //Get Platform and Ball
        userPlatform = this.findViewById(R.id.platform);
        pinBall = this.findViewById(R.id.ball);
        //Get brick layout
        //TODO DONT forget the bricks are inside another relative layout
        RelativeLayout layout = this.findViewWithTag("levelLayout");
        this.allBricks = BrickBuilder.GatherBricks(layout);
    }
}
