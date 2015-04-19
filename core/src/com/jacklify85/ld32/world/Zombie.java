package com.jacklify85.ld32.world;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.ai.Pathfinder;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.AudioUtil;
import com.jacklify85.ld32.util.RenderUtils;

public class Zombie extends EntityBase{

	private Pathfinder pFinder = null;
	private boolean ignited = false;
	
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
		
		if (this.ignited) {
			this.damage(0.1f);
		}
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.zombie, this.getX(), this.getY());
		
		if (this.ignited) {
			RenderUtils.drawTexture(LDGame.fire, this.getX() + 3, this.getY() + 25);
		}
	}

	@Override
	public float getHeight() {
		return LDGame.zombie.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.zombie.getWidth();
	}

	@Override
	protected void died() {
		GameScreen.score += 30;
		AudioUtil.playEffect(LDGame.zombieDie);
		//this.setBody(null);
	}

	public void ignite() {
		this.ignited = true;
	}
}