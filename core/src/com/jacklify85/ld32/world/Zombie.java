package com.jacklify85.ld32.world;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.ai.Pathfinder;
import com.jacklify85.ld32.util.RenderUtils;

public class Zombie extends EntityBase{

	private Pathfinder pFinder = null;
	
	public Zombie(float x, float y, int health, int maxHealth, int id) {
		super(x, y, health, maxHealth, id);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO: Give zombies their own texture and replace extremely ugly placeholders
		RenderUtils.drawTexture(LDGame.player, this.getX(), this.getY());
	}

	@Override
	public float getHeight() {
		return LDGame.player.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.player.getWidth();
	}

	@Override
	protected void died() {
		
	}

}
