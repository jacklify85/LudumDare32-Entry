package com.jacklify85.ld32.pickups;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.world.EntityBase;

public class AmmoPickup extends PickupBase{

	public AmmoPickup(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.ammoPickup, this.getPosition().x, this.getPosition().y);
	}

	@Override
	public float getHeight() {
		return LDGame.ammoPickup.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.ammoPickup.getWidth();
	}

	@Override
	protected void onUse(EntityBase e) {
		GameScreen.player.weapon.ammo += 100;
	}

}
