package com.jacklify85.ld32.world;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.util.RenderUtils;

public class Player extends EntityBase{

	public Player(float x, float y, int id) {
		super(x, y, 100, 100, id);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.player, this.getX(), this.getY());
	}

	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	protected void died() {
		
	}

}
