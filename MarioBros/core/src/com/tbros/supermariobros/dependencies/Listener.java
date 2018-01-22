package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Listener implements ContactListener {
	@Override
	public void beginContact(Contact contact) {
    Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
    System.out.println(fixA);
    System.out.println(fixB);
  }

  @Override
public void endContact(Contact contact) {


}

@Override
public void preSolve(Contact contact, Manifold oldManifold) {
  // TODO

}

@Override
public void postSolve(Contact contact, ContactImpulse impulse) {
  // TODO

}

}
