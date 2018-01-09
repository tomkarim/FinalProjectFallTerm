package com.tbros.supermariobros.dependencies;

import java.util.Random; // Used to randomize the x coordinate.
import com.badlogic.gdx.graphics.Texture; // Gives us the ability to print the desired image onto the screen.
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

public class Goompa extends Sprite {
  public float x, y;
  public Random random;

  public Goompa () {
    super(new Texture("goompa.png"));
    random = new Random();
    x = (int)(Gdx.graphics.getWidth() / 2) + random.nextInt(((int) (Gdx.graphics.getWidth() / 2 + 1))); // Spawn them at a random x-coordinate.
    y = 45; // Make them appear to be on the stage.
    super.setPosition(x, y);
  }
}
