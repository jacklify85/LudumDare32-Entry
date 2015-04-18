package com.jacklify85.ld32.pickups;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.world.EntityBase;

public class HealthPickup extends PickupBase{

	public HealthPickup(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.healthPickup, this.getPosition().x, this.getPosition().y);
	}

	@Override
	public float getHeight() {
		return LDGame.healthPickup.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.healthPickup.getWidth();
	}

	@Override
	protected void onUse(EntityBase e) {
		GameScreen.score += 10;
		e.setHealth(e.getMaxHealth());
	}
	
}
