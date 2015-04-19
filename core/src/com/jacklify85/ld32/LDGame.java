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
	public static Texture zombie = null;
	public static Texture bullet = null;
	public static Texture ammoPickup = null;
	public static Texture maxhealthPickup = null;
	public static Texture speedPickup = null;
	
	
	// Stage2d
	private Stage stage = null;
	private Label fpsLabel, healthLabel, scoreLabel, ammoLabel, posLabel, waveLabel, remainingLabel = null;
	
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
		
		LabelStyle lstyle = new LabelStyle();
		lstyle.font = RenderUtils.getFont();
		lstyle.fontColor = Color.WHITE;
		
	    this.fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), lstyle);
	    this.fpsLabel.setPosition(10, 700);
	    this.healthLabel = new Label("Health: 0 / 0", lstyle);
	    this.healthLabel.setPosition(10, 20);
	    this.scoreLabel = new Label("Score: 0", lstyle);
	    this.scoreLabel.setPosition(10, 40);
	    this.ammoLabel = new Label("Remaining Ammunition: 0 Bullets", lstyle);
	    this.ammoLabel.setPosition(10, 60);
	    this.posLabel = new Label("Position: (X: 0; Y:0", lstyle);
	    this.posLabel.setPosition(10,  80);
	    this.waveLabel = new Label("Wave: 1", lstyle);
	    this.waveLabel.setPosition(10, 100);
	    this.remainingLabel = new Label("Zombies Remaining: 0", lstyle);
	    this.remainingLabel.setPosition(10, 120);
	    
	    this.stage.addActor(this.fpsLabel);
	    this.stage.addActor(this.healthLabel);
	    this.stage.addActor(this.scoreLabel);
	    this.stage.addActor(this.ammoLabel);
	    this.stage.addActor(this.posLabel);
	    this.stage.addActor(this.waveLabel);
	    this.stage.addActor(this.remainingLabel);
	    
		// Init BOX2D
		Box2D.init();
		player = new Texture("Sprite-0001.png");
		healthPickup = new Texture("HealthPickupT.png");
		zombie = new Texture("zombie.png");
		bullet = new Texture("bullet.png");
		ammoPickup = new Texture("AmmoPickup.png");
		maxhealthPickup = new Texture("MaxHealthPickup.png");
		speedPickup = new Texture("SpeedBoost.png");
		
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
		this.scoreLabel.setText("Score: " + GameScreen.score);
		this.ammoLabel.setText("Remaining Ammunition: " + GameScreen.player.weapon.ammo + " bullets");
		this.posLabel.setText("Position: (X: " + GameScreen.player.getX() + "; Y: " + GameScreen.player.getY() + ")");
		this.waveLabel.setText("Wave: " + GameScreen.wave);
		this.remainingLabel.setText("Zombies Remaining: " + GameScreen.world.zombiesRemaining);
		stage.act();
		super.render();
		stage.draw();
	}
}
