package com.tbros.supermariobros.dependencies;

import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

public class Goompa extends Sprite {
  public float x, y;
  public Random random;

  public Goompa () {
    super(new Texture("goompa.png"));
    random = new Random();
    x = (int)(Gdx.graphics.getWidth() / 2) + random.nextInt(((int) (Gdx.graphics.getWidth() / 2 + 1)));
    y = 45;
    super.setPosition(x, y);
  }
}
