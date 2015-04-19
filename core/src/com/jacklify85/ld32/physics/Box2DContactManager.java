package com.jacklify85.ld32.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.jacklify85.ld32.pickups.HealthPickup;
import com.jacklify85.ld32.pickups.PickupBase;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.world.IGameObject;
import com.jacklify85.ld32.world.Player;
import com.jacklify85.ld32.world.UnconventionalBullet;
import com.jacklify85.ld32.world.Zombie;

public class Box2DContactManager implements ContactListener {

	public static boolean isContacted = false;
	
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
				isContacted = true;
			} else if (gObjectB instanceof PickupBase) {
				Player player = (Player)gObjectA;
				// prevent player from using health boost pickup if health is full
				if (player.getHealth() == player.getMaxHealth() && gObjectB instanceof HealthPickup) {
					return;
				}
				PickupBase pickup = (PickupBase)gObjectB;
				pickup.use(player);
			}
		} else if (gObjectA instanceof Zombie) {
			if (gObjectB instanceof Player) {
				Player player = (Player)gObjectB;
				player.damage(3);
				isContacted = true;
			} else if (gObjectB instanceof UnconventionalBullet) {
				Zombie zombie = (Zombie)gObjectA;
				zombie.damage(10);
				zombie.ignite();
				UnconventionalBullet bullet = (UnconventionalBullet)gObjectB;
				bullet.setHealth(0);
				GameScreen.score += 15;
			} else if (gObjectB instanceof Zombie) {
				//contact.getFixtureA().getBody().applyForce(20f, 10f, 1f, 1f, true);
			}
		} else if (gObjectA instanceof UnconventionalBullet) {
			if (gObjectB instanceof Zombie) {
				Zombie zombie = (Zombie)gObjectB;
				zombie.damage(10);
				zombie.ignite();
				GameScreen.score += 15;
			}
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		isContacted = false;
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		if ((contact.getFixtureA().getBody().getUserData() instanceof Player || contact.getFixtureA().getBody().getUserData() instanceof UnconventionalBullet) && (contact.getFixtureB().getBody().getUserData() instanceof Player || contact.getFixtureB().getBody().getUserData() instanceof UnconventionalBullet)) {
			contact.setEnabled(false);
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		if ((contact.getFixtureA().getBody().getUserData() instanceof Player || contact.getFixtureA().getBody().getUserData() instanceof UnconventionalBullet) && (contact.getFixtureB().getBody().getUserData() instanceof Player || contact.getFixtureB().getBody().getUserData() instanceof UnconventionalBullet)) {
			contact.setEnabled(false);
		}
	}

}
