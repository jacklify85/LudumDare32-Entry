package com.jacklify85.ld32;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class LDGame extends Game {
	
	// Do some logging
	private FPSLogger logger;
	
	// Asset management
	private AssetManager _aManager;
	public static Texture player = null;
	public static Texture healthPickup = null;
	
	@Override
	public void create () {
		Gdx.app.log("LDGAME", "INIT");
		Gdx.graphics.setTitle("Jacklify85's LD 32 Entry");
		// Log some debug information
		Gdx.app.log("LDGAME", "Platform Version: " + Gdx.app.getVersion());
		Gdx.app.log("LDGAME", "Native Heap: " + Gdx.app.getNativeHeap());
		Gdx.app.log("LDGAME", "Java Heap: " + Gdx.app.getJavaHeap());
		this.logger = new FPSLogger();
		this._aManager = new AssetManager();
		
		// Init BOX2D
		Box2D.init();
		player = new Texture("Sprite-0001.png");
		healthPickup = new Texture("HealthPickupT.png");
		// set screen to game screen
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		this.logger.log();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Start rendering
		RenderUtils.beginRendering();
		RenderUtils.renderText("FPS: " + Gdx.graphics.getFramesPerSecond(), -510, 350);
		if (GameScreen.player != null) {
			RenderUtils.renderText("Health: " + GameScreen.player.getHealth() + " / " + GameScreen.player.getMaxHealth(), -510, -340);
		}
		RenderUtils.stopRendering();
		super.render();
	}
}
