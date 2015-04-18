package com.jacklify85.ld32.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Box2DContactManager implements ContactFilter {

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Gdx.app.debug("LDGAME", "PhysicMgr: fixture collision (FIX A = " + fixtureA.toString() + "; FIX B = " + fixtureB.toString() + ")");
		return false;
	}

}
