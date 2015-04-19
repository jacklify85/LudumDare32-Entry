package com.jacklify85.ld32.world;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.jacklify85.ld32.screens.GameScreen;

public class UnconventionalBullet extends EntityBase implements Poolable{
	private int direction = 0;
	
	
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
	}

	@Override
	public void draw() {
		
	}

	@Override
	public float getHeight() {
		return 1;
	}

	@Override
	public float getWidth() {
		return 1;
	}

	@Override
	protected void died() {
		GWorld.bulletPool.free(this);
	}

	@Override
	public void reset() {
		this.direction = 0;
	}

	public void setDirection(int direction) {
		this.direction  = direction;
	}

}
