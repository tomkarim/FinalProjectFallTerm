package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.InputAdapter;

public class InputManager extends InputAdapter {
  private int key;
  private boolean keyPressed;

  @Override
  public boolean keyDown (int keycode) {
    switch (keycode) {
      case 19:
      key = 19;
      keyPressed = true;
      //System.out.println("Up");
      break;
      case 20:
      key = 20;
      keyPressed = true;
      //System.out.println("down");
      break;
      case 21:
      key = 21;
      keyPressed = true;
      //System.out.println("left");
      break;
      case 22:
      key = 22;
      keyPressed = true;
      //System.out.println("right");
      break;
    }
   return true;
 }

@Override
 public boolean keyUp (int keycode) {
   key = 0;
   keyPressed = false;
   return false;
 }

 @Override
 public boolean keyTyped (char character) {
   return false;
 }

 public int getKey () {
   return key;
 }

 public boolean KeyPressed () {
   return keyPressed;
 }

}
