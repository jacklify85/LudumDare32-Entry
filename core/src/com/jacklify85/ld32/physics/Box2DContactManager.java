package com.jacklify85.ld32.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jacklify85.ld32.pickups.PickupBase;
import com.jacklify85.ld32.world.IGameObject;
import com.jacklify85.ld32.world.Player;
import com.jacklify85.ld32.world.Zombie;

public class Box2DContactManager implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		IGameObject gObjectA = (IGameObject)contact.getFixtureA().getBody().getUserData();
		IGameObject gObjectB = (IGameObject)contact.getFixtureB().getBody().getUserData();
		
		if (gObjectA instanceof PickupBase) {
			if (gObjectB instanceof Player) {
				PickupBase pickup = (PickupBase)gObjectA;
				Player player = (Player)gObjectB;
				pickup.use(player);
			}
		} else if (gObjectA instanceof Player) {
			if (gObjectB instanceof Zombie) {
				Player player = (Player)gObjectA;
				player.damage(3);
			} else if (gObjectB instanceof PickupBase) {
				Player player = (Player)gObjectA;
				PickupBase pickup = (PickupBase)gObjectB;
				pickup.use(player);
			}
		} else if (gObjectA instanceof Zombie) {
			if (gObjectB instanceof Player) {
				Player player = (Player)gObjectB;
				player.damage(3);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
