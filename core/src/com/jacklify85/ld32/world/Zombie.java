package com.jacklify85.ld32.world;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.ai.Pathfinder;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class Zombie extends EntityBase{

	private Pathfinder pFinder = null;
	
	public Zombie(float x, float y, int id) {
		super(x, y, 50, 50, id);
		this.pFinder = new Pathfinder(this, GameScreen.player);
	}

	@Override
	public void update() {
		if (this.pFinder.getMovementX() > 0) {
			if (this.pFinder.getMovementY() > 0) {
				GameScreen.world.doMovement(this, this.getX() + 0.6f, this.getY() + 0.6f);
			} else if (this.pFinder.getMovementY() < 0) {
				GameScreen.world.doMovement(this, this.getX() + 0.6f, this.getY() - 0.6f);
			} else {
				GameScreen.world.doMovement(this, this.getX() + 0.6f, this.getY());
			}
		} else if (this.pFinder.getMovementX() < 0) {
			if (this.pFinder.getMovementY() > 0) {
				GameScreen.world.doMovement(this, this.getX() - 0.6f, this.getY() + 0.6f);
			} else if (this.pFinder.getMovementY() < 0) {
				GameScreen.world.doMovement(this, this.getX() - 0.6f, this.getY() - 0.6f);
			} else {
				GameScreen.world.doMovement(this, this.getX() - 0.6f, this.getY());
			}
		} else {
			if (this.pFinder.getMovementY() > 0) {
				GameScreen.world.doMovement(this, this.getX(), this.getY() + 0.6f);
			} else if (this.pFinder.getMovementY() < 0) {
				GameScreen.world.doMovement(this, this.getX(), this.getY() - 0.6f);
			}
		}
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
