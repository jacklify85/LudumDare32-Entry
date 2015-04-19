package com.jacklify85.ld32.weapons;

import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.world.EntityBase;
import com.jacklify85.ld32.world.GWorld;
import com.jacklify85.ld32.world.UnconventionalBullet;

public class UnconventionalWeapon implements IWeapon{

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHit(EntityBase target) {
		target.damage(25f);
	}

	@Override
	public void use() {
		// Get bullet from pool
		UnconventionalBullet uBullet = GWorld.bulletPool.obtain();
	    uBullet.setDirection(GameScreen.player.direction);
	    uBullet.setPosition(GameScreen.player.getX(), GameScreen.player.getY());
		GameScreen.world.addObject(uBullet);
	}
}
