package com.jacklify85.ld32.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jacklify85.ld32.world.EntityBase;

public class Box2DContactManager implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		//Gdx.app.log("LDGAME", "PHYSICS: Contact occurred.1");
		EntityBase eBase = (EntityBase)contact.getFixtureA().getBody().getUserData();
		eBase.damage(1);
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		//Gdx.app.log("LDGAME", "PHYSICS: Contact occurred.2");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		//Gdx.app.log("LDGAME", "PHYSICS: Contact occurred.3");
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		//Gdx.app.log("LDGAME", "PHYSICS: Contact occurred.4");
	}

}
