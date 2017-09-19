package com.tuffstudios.GameObjects;

/*

GRASS CLASS
This class is an extension of the scrollable class because the grass consists
of pieces which are moved and needs to be scrollable. This class handles all
interactions that the grass has with the game world. Mainly just moving
across the screen.

 */

public class Grass extends Scrollable {
	public Grass(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}

	//resets the grass's position when the game is restarted
	public void onRestart(float x, float scrollSpeed) {
		position.x = x;
		velocity.x = scrollSpeed;
	}
}
