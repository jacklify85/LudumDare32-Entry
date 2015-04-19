package com.jacklify85.ld32.weapons;

import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.world.EntityBase;
import com.jacklify85.ld32.world.GWorld;
import com.jacklify85.ld32.world.UnconventionalBullet;

public class UnconventionalWeapon implements IWeapon{

	public int ammo = 10000;
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHit(EntityBase target) {
		target.damage(25f);
	}

	@Override
	public void use(int xPos, int yPos) {
		// Get bullet from pool
		if (ammo <= 0) {
			return;
		}
		ammo--;
		UnconventionalBullet uBullet = GWorld.bulletPool.obtain();
	    uBullet.setDirection(GameScreen.player.direction);
	    uBullet.setTarget(xPos, yPos);
	    uBullet.setPosition(GameScreen.player.getX(), GameScreen.player.getY());
		GameScreen.world.addObject(uBullet);
	}

	@Override
	public void use() {
		if (ammo <= 0) {
			return;
		}
		ammo--;
		UnconventionalBullet uBullet = GWorld.bulletPool.obtain();
	    uBullet.setDirection(GameScreen.player.direction);
	    uBullet.setPosition(GameScreen.player.getX(), GameScreen.player.getY());
		GameScreen.world.addObject(uBullet);
	}
}
