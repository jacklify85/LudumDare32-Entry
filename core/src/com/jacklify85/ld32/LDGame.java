package com.jacklify85.ld32;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.jacklify85.ld32.screens.GameScreen;
import com.jacklify85.ld32.util.AudioUtil;
import com.jacklify85.ld32.util.RenderUtils;

public class LDGame extends Game {
	// Asset management (crappy)
	
	public static Texture player = null;
	public static Texture healthPickup = null;
	public static Texture zombie = null;
	public static Texture bullet = null;
	public static Texture ammoPickup = null;
	public static Texture maxhealthPickup = null;
	public static Texture speedPickup = null;
	public static Texture fire = null;
	public static Texture playerDown = null;
	public static Texture playerUp = null;
	public static Texture playerRight = null;
	public static Texture playerLeft = null;
	
	public static Sound hitSound = null;
	public static Sound powerupSound = null;
	public static Sound playerHurt = null;
	public static Sound zombieDie = null;
	
	public static Music gameOver = null;
	
	private int highScore = 0;
	private Preferences preferences;
	
	// Stage2d
	private Stage stage = null;
	private Label fpsLabel, healthLabel, scoreLabel, ammoLabel, posLabel, waveLabel, remainingLabel, endLabel, pauseLabel = null;

	private boolean died;
	
	@Override
	public void create () {
		Gdx.app.log("LDGAME", "INIT (Super ZDefeat by Jacklify85)");
		Gdx.graphics.setTitle("Super ZDefeat by Jacklify85");
		// Log some debug information
		Gdx.app.log("LDGAME", "Platform Version: " + Gdx.app.getVersion());
		Gdx.app.log("LDGAME", "Native Heap: " + Gdx.app.getNativeHeap());
		Gdx.app.log("LDGAME", "Java Heap: " + Gdx.app.getJavaHeap());
		
		this.preferences = Gdx.app.getPreferences("jacklify85LD32");
		
		if (this.preferences.contains("highscore")) {
			this.highScore = this.preferences.getInteger("highscore", 0);
		}
		
		// create stage
		this.stage = new Stage();
		
		LabelStyle lstyle = new LabelStyle();
		lstyle.font = RenderUtils.getFont();
		lstyle.fontColor = Color.WHITE;
		
	    this.fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), lstyle);
	    this.fpsLabel.setColor(Color.TEAL);
	    this.fpsLabel.setPosition(10, 700);
	    this.healthLabel = new Label("Health: 0 / 0", lstyle);
	    this.healthLabel.setColor(Color.RED);
	    this.healthLabel.setPosition(10, 20);
	    this.scoreLabel = new Label("Score: 0", lstyle);
	    this.scoreLabel.setPosition(10, 40);
	    this.ammoLabel = new Label("Remaining Ammunition: 0 Bullets", lstyle);
	    this.ammoLabel.setColor(Color.ORANGE);
	    this.ammoLabel.setPosition(10, 60);
	    this.posLabel = new Label("Position: (X: 0; Y:0", lstyle);
	    this.posLabel.setPosition(10,  80);
	    this.waveLabel = new Label("Wave: 1", lstyle);
	    this.waveLabel.setPosition(10, 100);
	    this.remainingLabel = new Label("Zombies Remaining: 0", lstyle);
	    this.remainingLabel.setPosition(10, 120);
	    this.remainingLabel.setColor(Color.GREEN);
	    this.endLabel = new Label("You've been eaten! You're score was: SCORE Thanks for playing! Press 'ENTER' to start a new game.", lstyle);
	    this.endLabel.setPosition(100, 100);
	    this.endLabel.setVisible(false);
	    this.pauseLabel = new Label("Game has been paused! Press the 'ESCAPE' key to continue!", lstyle);
	    this.pauseLabel.setPosition(100, 150);
	    this.pauseLabel.setVisible(false);
	    
	    this.stage.addActor(this.fpsLabel);
	    this.stage.addActor(this.healthLabel);
	    this.stage.addActor(this.scoreLabel);
	    this.stage.addActor(this.ammoLabel);
	    this.stage.addActor(this.posLabel);
	    this.stage.addActor(this.waveLabel);
	    this.stage.addActor(this.remainingLabel);
		this.stage.addActor(this.endLabel);
		this.stage.addActor(this.pauseLabel);
	    
		// Init BOX2D
		Box2D.init();
		player = new Texture("Sprite-0001.png");
		healthPickup = new Texture("HealthPickupT.png");
		zombie = new Texture("zombie.png");
		bullet = new Texture("bullet2.png");
		ammoPickup = new Texture("AmmoPickup.png");
		maxhealthPickup = new Texture("MaxHealthPickup.png");
		speedPickup = new Texture("SpeedBoost.png");
		fire = new Texture("fire.png");
		playerDown = new Texture("PlayerDown.png");
		playerUp = new Texture("PlayerUp.png");
		playerRight = new Texture("PlayerRight.png");
		playerLeft = new Texture("PlayerLeft.png");
		
		hitSound = Gdx.audio.newSound(Gdx.files.internal("EntityHit.wav"));
		powerupSound = Gdx.audio.newSound(Gdx.files.internal("Powerup.wav"));
		playerHurt = Gdx.audio.newSound(Gdx.files.internal("BWU.wav"));
		zombieDie = Gdx.audio.newSound(Gdx.files.internal("ZombieDie.wav"));
		
		gameOver = Gdx.audio.newMusic(Gdx.files.internal("bu-shields-of-puppies.ogg"));
		// set screen to game screen
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		AudioUtil.pulse();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
		
		// Start rendering
		if (!died) {
			this.fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
			this.healthLabel.setText("Health: " + GameScreen.player.getHealth() + " / " + GameScreen.player.getMaxHealth());
			this.scoreLabel.setText("Score: " + GameScreen.score);
			this.ammoLabel.setText("Remaining Ammunition: " + GameScreen.player.weapon.ammo + " bullets");
			this.posLabel.setText("Position: (X: " + GameScreen.player.getX() + "; Y: " + GameScreen.player.getY() + ")");
			this.waveLabel.setText("Wave: " + GameScreen.wave);
			this.remainingLabel.setText("Zombies Remaining: " + GameScreen.world.zombiesRemaining);
		} else {
			this.endLabel.setText("You've been eaten! Your score was: " + GameScreen.score + " Your high score is: " + this.highScore + " Thanks for playing! Press 'ENTER' to start a new game.");
			if (GameScreen.score > this.highScore) {
				this.preferences.putInteger("highscore", GameScreen.score);
				this.highScore = GameScreen.score;
				this.preferences.flush();
			}
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
		    	// Reset game
		    	this.died = false;
				this.fpsLabel.setVisible(true);
				this.healthLabel.setVisible(true);
				this.ammoLabel.setVisible(true);
				this.posLabel.setVisible(true);
				this.waveLabel.setVisible(true);
				this.remainingLabel.setVisible(true);
				this.scoreLabel.setVisible(true);
				this.endLabel.setVisible(false);
				GameScreen.score = 0;
				GameScreen.enableSpeedBoost = false;
				GameScreen.alive = true;
				GameScreen.wave = 1;
				GameScreen.world.dispose();
				GameScreen.world = null;
				GameScreen.player = null;
				GameScreen.go = false;
				this.setScreen(new GameScreen(this));
		    }
		}
		stage.act(); 
		stage.draw();
	}

	public void playerDied() {
		if (this.died) return;
		this.died = true;
		this.endLabel.setVisible(true);
		this.fpsLabel.setVisible(false);
		this.healthLabel.setVisible(false);
		this.ammoLabel.setVisible(false);
		this.posLabel.setVisible(false);
		this.waveLabel.setVisible(false);
		this.remainingLabel.setVisible(false);
		this.scoreLabel.setVisible(false);
	}
	
	public void callPause() {
		this.pauseLabel.setVisible(true);
	}
	
	public void callResume() {
		this.pauseLabel.setVisible(false);
	}
}
