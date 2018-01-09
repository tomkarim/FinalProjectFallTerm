package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.graphics.Texture; // Gives us the ability to print the desired image onto the screen.
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
  public boolean canJump;
  public float x, y;

  /*
    Constructor -
    Returns a player and sets its inital position to the bottom left of the.
    We made the y-coordinate slighty above 0 so that Mario actually looks like hes on the platform.
    Once mario, is in the air, he should not have the liberty to jump. Therefore, we have a canJump property that is set to false when he is in air, and true when he makes contact with the ground.
  */
  public Player () {
    super(new Texture("mario.png"));
    x = 0;
    y = 50;
    super.setPosition(x, y);
    canJump = true;
  }

  public void update (int keycode) {
    switch (keycode) {
      case 19: // Move him up when he jumps.
      if (!canJump && y > 65) break; //If he is in the air, he cannot jump, therefore we don't do anything.
      y += 150;
      canJump = false;
      break;
      case 21: // Move left
      x -= 5;
      break;
      case 22: //Move right
      x += 5;
      break;
    }

    System.out.println("(" + x + ", " + y + ")"); //Used for debugging, will comment out later.
  }

}
