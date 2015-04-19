package com.jacklify85.ld32.world;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class UnconventionalBullet extends EntityBase implements Poolable{
	private int direction = 0;
	
	private final int maxCycles = 60 * 2;
	private int elapsedCycles = 0;
	
	public UnconventionalBullet() {
		super (0f, 0f, 1f, 1f, 0);
	}

	@Override
	public void update() {
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
		elapsedCycles++;
		if (elapsedCycles >= maxCycles) {
			this.setHealth(0);
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
		GWorld.bulletPool.free(this);
	}

	@Override
	public void reset() {
		this.direction = 0;
		this.elapsedCycles = 0;
	}

	public void setDirection(int direction) {
		this.direction  = direction;
	}

}
