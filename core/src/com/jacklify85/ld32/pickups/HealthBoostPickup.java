package com.jacklify85.ld32.pickups;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.world.EntityBase;

public class HealthBoostPickup extends PickupBase{

	public HealthBoostPickup(float x, float y) {
		super(x, y);
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.maxhealthPickup, this.getPosition().x, this.getPosition().y);
	}

	@Override
	public float getHeight() {
		return LDGame.maxhealthPickup.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.maxhealthPickup.getWidth();
	}

	@Override
	protected void onUse(EntityBase e) {
		e.setMaxHealth(e.getMaxHealth() + 50);
	}
}
