package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.graphics.Texture; // Gives us the ability to print the desired image onto the screen.
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.tbros.supermariobros.MarioBros;

public class Mario extends Sprite {
  public World world;
  public Body mbody;
  public boolean canJump;
  /*
    Constructor -
    Returns a player and sets its inital position to the bottom left of the.
    We made the y-coordinate slighty above 0 so that Mario actually looks like hes on the platform.
    Once mario, is in the air, he should not have the liberty to jump. Therefore, we have a canJump property that is set to false when he is in air, and true when he makes contact with the ground.
  */
  public Mario(World world) {
      this.world = world;
      setMbody();
      canJump = true;
  }

  public void setMbody(){
    BodyDef bodydef = new BodyDef();
    bodydef.position.set(32, 32); //setting initial position
    bodydef.type = BodyDef.BodyType.DynamicBody; //dynamic bodies are reactive to forces like collisions and gravity
    mbody = world.createBody(bodydef); //creating marios body in the world

    FixtureDef fixdef = new FixtureDef();
    CircleShape shape = new CircleShape();
    shape.setRadius(6); //setting radius of circle to 6 for now
    fixdef.shape = shape; //adding this circle to the fixture
   // fixdef.density = 1;
    mbody.createFixture(fixdef); //adding this fixture to the body

  }



}
