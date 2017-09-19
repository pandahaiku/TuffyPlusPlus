package com.tuffstudios.GameObjects;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.tuffstudios.TPPHelpers.AssetLoader;

/*

ELEPHANT CLASS
This class handles all interactions with the elephant and the objects in the
game world. This class's member functions hold values for the location of
the elephant as well as those determining movement.

 */

public class Elephant {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private float height;
    private float originalY;
    private boolean isAlive;
    private Circle boundingCircle;

    public Elephant(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        this.originalY = y;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
        boundingCircle = new Circle();
        isAlive = true;
    }

    //this function updates the screen and is called very frequently
    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        // VELOCITY CAP
        if (velocity.y > 200) {
            velocity.y = 200;
        }

        // CEILING CHECK
        if (position.y < -13) {
            position.y = -13;
            velocity.y = 0;
        }

        position.add(velocity.cpy().scl(delta));

        // Set the circle's center to be (9, 6) with respect to the elephant.
        // Set the circle's radius to be 6.5f;
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Rotate counterclockwise
        // When moving upward
        if (velocity.y < 0) {
            rotation -= 600 * delta;
            if (rotation < -20) {
                rotation = -20;
            }
        }

        // Rotate clockwise
        // When falling
        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    //The updateReady function handles the initial parameters for the elephant
    public void updateReady(float runTime) {
        position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() { return velocity.y > 70 || !isAlive; }

    //Whenever the screen is clicked, a noise plays and the elephant moves upward
    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play(AssetLoader.VOLUME);
            velocity.y = -140;
        }
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }

    public void decelerate() {
        acceleration.y = 0;
    }

    //When restarted, this function resets all values to their
    //initial values and takes an int as the starting position
    public void onRestart(int y) {
        rotation = 0;
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
    }

    //GETTERS
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    //Check if elephant is alive
    public boolean isAlive() {
        return isAlive;
    }
}