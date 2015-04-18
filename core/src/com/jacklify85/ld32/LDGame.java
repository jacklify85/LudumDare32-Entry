package com.jacklify85.ld32;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.RenderUtils;

public class LDGame extends Game {
	
	// Do some logging
	private FPSLogger logger;
	
	// Asset management (crappy)
	
	public static Texture player = null;
	public static Texture healthPickup = null;
	
	// Stage2d
	private Stage stage = null;
	private Label fpsLabel, healthLabel = null;
	@Override
	public void create () {
		Gdx.app.log("LDGAME", "INIT");
		Gdx.graphics.setTitle("Jacklify85's LD 32 Entry");
		// Log some debug information
		Gdx.app.log("LDGAME", "Platform Version: " + Gdx.app.getVersion());
		Gdx.app.log("LDGAME", "Native Heap: " + Gdx.app.getNativeHeap());
		Gdx.app.log("LDGAME", "Java Heap: " + Gdx.app.getJavaHeap());
		this.logger = new FPSLogger();
		
		// create stage
		this.stage = new Stage();
		//stage.setViewport(new ScreenViewport());
		
		LabelStyle lstyle = new LabelStyle();
		lstyle.font = RenderUtils.getFont();
		lstyle.fontColor = Color.WHITE;
		
	    this.fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), lstyle);
	    this.fpsLabel.setPosition(10, 700);
	    this.healthLabel = new Label("Health: 0 / 0", lstyle);
	    this.healthLabel.setPosition(10, 20);
	    
	    this.stage.addActor(this.fpsLabel);
	    this.stage.addActor(this.healthLabel);
	    
	    
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
		this.fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
		this.healthLabel.setText("Health: " + GameScreen.player.getHealth() + " / " + GameScreen.player.getMaxHealth());
		stage.act();
		super.render();
		stage.draw();
	}
}
