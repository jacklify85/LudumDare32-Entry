package com.jacklify85.ld32.world;

import com.badlogic.gdx.Gdx;
import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class Player extends EntityBase{

	public Player(float x, float y, int id) {
		super(x, y, 100, 100, id);
	}

	@Override
	public void update() {
		Gdx.app.log("LDGAME", "PHEALTH: " + this.health);
	}

	@Override
	public void draw() {
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
		GameScreen.alive = false;
	}

}
