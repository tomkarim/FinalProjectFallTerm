package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.InputAdapter;

public class InputManager extends InputAdapter {
  public boolean keyDown (int keycode) {
    switch (keycode) {
      case 19:
      System.out.println("Up");
      break;
      case 20:
      System.out.println("down");
      break;
      case 21:
      System.out.println("left");
      break;
      case 22:
      System.out.println("right");
      break;
    }
   return false;
 }
 public boolean keyUp (int keycode) {
   return false;
 }
}
