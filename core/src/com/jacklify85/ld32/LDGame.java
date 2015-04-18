package com.jacklify85.ld32;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class LDGame extends Game {
	
	// Do some logging
	private FPSLogger logger;
	
	// Asset management
	private AssetManager _aManager;
	
	@Override
	public void create () {
		Gdx.app.log("LDGAME", "INIT");
		this.logger = new FPSLogger();
		this._aManager = new AssetManager();
		
		// Init BOX2D
		Box2D.init();
		
		// set screen to game screen
		this.setScreen(new GameScreen());
	}

	@Override
	public void render () {
		this.logger.log();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Start rendering
		RenderUtils.beginRendering();
		RenderUtils.renderText("FPS: " + Gdx.graphics.getFramesPerSecond(), 100, 100);
		RenderUtils.stopRendering();
		super.render();
	}
}
