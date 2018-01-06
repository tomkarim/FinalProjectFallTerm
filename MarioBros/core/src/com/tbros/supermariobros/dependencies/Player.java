package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
  private Sprite sprite;
  private int xcor;
  private int ycor;

  public Player () {
    sprite = new Sprite(new Texture("goompa.png"));
    xcor = 0;
    ycor = 0;
  }

  public Sprite getSprite() {
    return sprite;
  }

  public int getXCor () {
    return xcor;
  }

  public int getYCor () {
    return ycor;
  }

}
