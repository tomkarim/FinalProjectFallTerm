package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {
  public boolean canJump;
  public float x, y;


  public Player () {
    super(new Texture("mario.png"));
    x = 0;
    y = 50;
    super.setPosition(x, y);
    canJump = true;
  }

  public void update (int keycode) {
    switch (keycode) {
      case 19: //Up
      if (!canJump && y > 65) break;
      y += 150;
      canJump = false;
      break;
      case 21:
      x -= 5;
      break;
      case 22:
      x += 5;
      break;
    }

    System.out.println("(" + x + ", " + y + ")");
  }

}
