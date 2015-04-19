package com.jacklify85.ld32.pickups;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.world.EntityBase;

public class SpeedPickup extends PickupBase{

	public SpeedPickup(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.speedPickup, this.getPosition().x, this.getPosition().y);
	}

	@Override
	public float getHeight() {
		return LDGame.speedPickup.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.speedPickup.getWidth();
	}

	@Override
	protected void onUse(EntityBase e) {
		GameScreen.enableSpeedBoost = true;
	}
}
