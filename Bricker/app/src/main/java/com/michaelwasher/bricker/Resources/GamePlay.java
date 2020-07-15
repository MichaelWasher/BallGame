package com.michaelwasher.bricker.Resources;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.michaelwasher.bricker.R;
import com.michaelwasher.bricker.activities.GameActivity;
import com.michaelwasher.bricker.views.Ball;
import com.michaelwasher.bricker.views.Platform;
import com.michaelwasher.bricker.views.bricks.Brick;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GamePlay extends DrawingView {
    //Class-Scope Variables
    public int movement = 0;
    //Starting Values
    protected int startSpeed;
    protected int currentSpeed;
    protected int collisionAccelerator;

    protected int numberOfLivesLeft;
    protected float EPSILON = 0.01f;

    //Logic Control Variables
    protected boolean gameStarted = false;
    protected int score = 0;
    protected int scoreMultiplier = 10;

    protected int levelResource;
    protected int levelId;

    V2 ballStartPosition;
    V2 platformStartPosition;

    boolean updated = false;

    public int getLives(){
        return this.numberOfLivesLeft;
    }

    public boolean getUpdated(){
        return updated;
    }
    public int getScore(){
        return this.score;
    }
    public int getLevel(){
        return this.levelId;
    }
    //Displayable Objects
    ArrayList<Brick> allBricks;
    Ball pinBall;
    Platform userPlatform;

    //String for Text
    Context context;

    GameLoop gameLoop;

    public GamePlay(Context context, AttributeSet attr) {
        super(context, attr);
        Log.d("GamePlay Created", "A GamePlay has been created.");
        this.context = context;
        gameLoop = new GameLoop(((GameActivity)context), this);

        // Set up the level include


    }


    public void startGame(LevelStartInformation startInfo) {
        //Initialize all Class-Scope values
        setStartInformation(startInfo, this.context);
        createLevel();
        startGame();
    }



    @Override
    public void invalidate() {
        //Queue Redraw
        super.invalidate();
        for (Brick brick : this.allBricks) {
            brick.invalidate();
        }
        pinBall.invalidate();
        userPlatform.invalidate();
    }

    protected void checkMovePlatform() {
        Log.i("Movement: ", String.valueOf(this.movement));
        if (this.movement != 0) {
            movePlatform(10);
            this.movement = 0;
        }
    }

    protected void onUpdate() {
        int tmpScore = this.score;
        int tmpLives = this.numberOfLivesLeft;

        if (this.getWidth() <= 0)
            return;
        //Move Ball
        moveBall();
        // Requires for keeping a,b,c,d in sync
        if (this.userPlatform != null)
            userPlatform.updateValues();

        //Check for Collision with Bricks
        checkBrickCollision();
        checkBoundaryCollision();
        checkPlatformCollision();

        this.updated = tmpScore != this.score || tmpLives != this.numberOfLivesLeft;
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
        gameLoop.setRunning(false);
        ((GameActivity)this.context).endGame();
    }

    //Game Progress
    protected void lossOfLife() {
        //player has died
        numberOfLivesLeft--;
        Log.d("Life Lost", "A life was lost.");
        if (numberOfLivesLeft > 0)
            resetGame();
        else
            endGame();
    }


    //Game Logic and Management
    protected void resetGame() {
        //Reset all bricks, reset ball, reset platform.
        for(final Brick b : this.allBricks){
            ((Activity)this.context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    b.setVisibility(View.VISIBLE);
                }
            });
        }
        this.userPlatform.reposition((int)this.platformStartPosition.x, (int)this.platformStartPosition.y);
        this.pinBall.reposition((int)this.ballStartPosition.x, (int)this.ballStartPosition.y);
        this.score = 0;
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

        // DEBUG Bounce off the ground
//        if (pinBall.getCentre().y + pinBall.getRadius() > this.getHeight()) {
//            pinBall.direction.y = -pinBall.direction.y;
//            return;
//        }

        //Check if Played has missed the ball
        if (pinBall.getCentre().y + pinBall.getRadius() > this.getHeight()) {
            Log.d("life loss values", "Pinball.Y: " + String.valueOf(pinBall.getCentre().y) +
                    "Pinball Radius: " + String.valueOf(pinBall.getRadius()) + " height: " + String.valueOf(this.getHeight()));
            lossOfLife();
        }
    }

    protected boolean checkBrickCollision() {
        //Check for any collisons with bricks
        if (this.allBricks == null)
            return false;

        for (final Brick b : this.allBricks) {
            // If brick is not visible; ignore
            if(b.getVisibility() == View.INVISIBLE){
                continue;
            }

            //Collision Occurred, reflect and make brick invisible
            if (b.intersectBall(pinBall.getCentre(), pinBall.getRadius())) {
                //TODO Convert to b.reflectBall()
                if (pinBall.getCentre().y - pinBall.getRadius() < b.getStartPoint().y + b.getHeight() ||
                        pinBall.getCentre().y + pinBall.getRadius() > b.getStartPoint().y) {
                    pinBall.direction.y = -pinBall.direction.y;
                } else {
                    pinBall.direction.x = -pinBall.direction.x;
                }

                //Remove Brick and increase players score
                ((Activity)this.context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        b.setVisibility(View.INVISIBLE);
                    }
                });

                score += scoreMultiplier;
                Log.i("Score Changed: ", String.valueOf(this.score));
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
            // Move back if going off screen
            // TODO if close to wall, move to the wall
            movement = 0;
        }

        userPlatform.repositionRelative(movement, 0);
    }


    //Initial Design
    private void setStartInformation(LevelStartInformation startInfo, Context context) {
        //Set Starting Values from LevelStartInfo
        this.startSpeed = currentSpeed = startInfo.startSpeed;
        this.collisionAccelerator = startInfo.collisionAccelerator;
        this.numberOfLivesLeft = startInfo.startNumberOfLives;
        this.score = 0;
        this.levelId = startInfo.levelNumber;
        this.levelResource = startInfo.levelResource;

    }

    private void createLevel() {
        //Get Platform and Ball
        userPlatform = this.findViewById(R.id.platform);
        pinBall = this.findViewById(R.id.ball);

        this.ballStartPosition = new V2(pinBall.getX(), pinBall.getY());
        this.platformStartPosition = new V2(userPlatform.getX(), userPlatform.getY());

        // Set the brick layout
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.level_one, this);
        RelativeLayout levelLayout = this.findViewWithTag("levelLayout");
        this.removeView(levelLayout);
        this.addView(levelLayout,0);

        // Set List of Bricks
        this.allBricks = BrickBuilder.GatherBricks(levelLayout);
    }
}
