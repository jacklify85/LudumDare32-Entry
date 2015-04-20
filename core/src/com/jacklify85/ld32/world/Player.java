package com.jacklify85.ld32.world;

import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.weapons.UnconventionalWeapon;

public class Player extends EntityBase{

	public int direction = 0; // 0 = UP, 1 = DOWN, -1 = LEFT, -2 = RIGHT
	public UnconventionalWeapon weapon;
	
	public Player(float x, float y, int id) {
		super(x, y, 100, 100, id);
		this.weapon = new UnconventionalWeapon();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw() {
		switch (this.direction) {
		case 1: {
			RenderUtils.drawTexture(LDGame.playerDown, this.getX(), this.getY());
			break;
		}
		case 0: {
			RenderUtils.drawTexture(LDGame.playerUp, this.getX(), this.getY());
			break;
		}
		case -2: {
			RenderUtils.drawTexture(LDGame.playerRight, this.getX(), this.getY());
			break;
		}
		case -1: {
			RenderUtils.drawTexture(LDGame.playerLeft, this.getX(), this.getY());
			break;
		}
		default: {
			RenderUtils.drawTexture(LDGame.player, this.getX(), this.getY());
			break;
		}
		}
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
		GameScreen.alive = false;
	}

}
