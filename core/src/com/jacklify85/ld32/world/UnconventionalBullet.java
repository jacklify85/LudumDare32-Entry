package com.jacklify85.ld32.world;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.ai.PositionPathfinder;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class UnconventionalBullet extends EntityBase implements Poolable{
	private int direction = 0;
	
	private final int maxCycles = 60 * 4;
	private int elapsedCycles = 0;
	private boolean isUsingTarget = false;
	private PositionPathfinder pFinder = null;
	
	public UnconventionalBullet() {
		super (0f, 0f, 1f, 1f, 0);
	}

	@Override
	public void update() {
		elapsedCycles++;
		if (elapsedCycles >= maxCycles) {
			this.setHealth(0);
		}
		if (!isUsingTarget) {
			switch (this.direction) {
			case 0: {
				GameScreen.world.doMovement(this, this.getX() + 0.2f, this.getY() + 1.0f);
				break;
			}
			case 1: {
				GameScreen.world.doMovement(this, this.getX() + 0.2f, this.getY() - 1.0f);
				break;
			}
			case -1: {
				GameScreen.world.doMovement(this, this.getX() - 1.0f, this.getY() + 0.2f);
				break;
			}
			case -2: {
				GameScreen.world.doMovement(this, this.getX() + 1.0f, this.getY() + 0.2f);
				break;
			}
		  }
		} else {
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
	}

	@Override
	public void draw() {
		RenderUtils.drawTexture(LDGame.bullet, this.getX(), this.getY());
	}

	@Override
	public float getHeight() {
		return LDGame.bullet.getHeight();
	}

	@Override
	public float getWidth() {
		return LDGame.bullet.getWidth();
	}

	@Override
	protected void died() {
		GameScreen.world.cleanupBody(this.getBody());
		this.setBody(null);
		GWorld.bulletPool.free(this);
	}

	@Override
	public void reset() {
		this.direction = 0;
		this.elapsedCycles = 0;
		this.isUsingTarget = false;
		this.pFinder = null;
		this.isDead = false;
		this.setHealth(1);
	}

	public void setDirection(int direction) {
		this.direction  = direction;
	}

	public void setTarget(int xPos, int yPos) {
		this.isUsingTarget = true;
		this.pFinder = new PositionPathfinder(this, xPos, yPos);
	}

}
