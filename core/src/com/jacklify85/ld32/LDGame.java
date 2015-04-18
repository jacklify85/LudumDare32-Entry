package com.jacklify85.ld32;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LDGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	// Do some logging
	private FPSLogger logger;
	
	@Override
	public void create () {
		Gdx.app.log("LDGAME", "INIT");
		batch = new SpriteBatch();
		this.logger = new FPSLogger();
	}

	@Override
	public void render () {
		this.logger.log();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.end();
	}
}
