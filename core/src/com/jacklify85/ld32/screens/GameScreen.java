package com.jacklify85.ld32.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.jacklify85.ld32.LDGame;
import com.jacklify85.ld32.pickups.HealthPickup;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.world.GWorld;
import com.jacklify85.ld32.world.Player;
import com.jacklify85.ld32.world.Zombie;

public class GameScreen implements Screen{

	private Camera camera = null;
	public static GWorld world;
	public static Player player;
	private boolean isPaused = false;
	private int score = 0;
	public static volatile boolean alive = true;
	private LDGame game;
	private Box2DDebugRenderer box2d;
	
	public GameScreen(LDGame game) {
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		GameScreen.world = new GWorld();
		GameScreen.player = new Player(159, 122, 21);
		GameScreen.world.addObject(player);
		this.game = game;
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			Zombie zombie = new Zombie(random.nextInt(500), random.nextInt(1000), 22 + i);
			this.world.addObject(zombie);
			this.box2d = new Box2DDebugRenderer();
		}
		
		for (int i = 0; i < 10; i++) {
			HealthPickup hPickup = new HealthPickup(random.nextInt(500), random.nextInt(300), 214);
			this.world.addObject(hPickup);
		}
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		doInput();
		// Set projection matrix and render
		RenderUtils.setMatrix(this.camera.combined);
		RenderUtils.beginRendering();
		//////////////////
		if (!this.isPaused) {
			//this.box2d.render(this.world.world, this.camera.combined);
			GameScreen.world.render();
			if (GameScreen.alive == false) {
				// player died, show dead screen
				this.game.setScreen(new DeadScreen(score));
			}
		} else {
			RenderUtils.renderText("Game is paused! To resume press escape!", 50, 25);
		}
		/////////////////
		RenderUtils.stopRendering();
	}

	private void doInput() {
		// TODO: IMPLEMENT MOBILE TOUCH SCREEN FOR ANDROID
		if (player == null) {
			return;
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			GameScreen.world.doMovement(player, player.getX(), player.getY() + 10.5f);
		}
		
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			GameScreen.world.doMovement(GameScreen.player, GameScreen.player.getX() - 10.5f, GameScreen.player.getY());
		}
		
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			GameScreen.world.doMovement(GameScreen.player, GameScreen.player.getX() + 10.5f, GameScreen.player.getY());
		}
		
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			GameScreen.world.doMovement(GameScreen.player, GameScreen.player.getX(), GameScreen.player.getY() - 10.5f);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			// Pause game or unpause
			this.isPaused = !this.isPaused;
		}
	}

	@Override
	public void resize(int width, int height) {
	
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		if (GameScreen.world != null) {
			GameScreen.world.dispose();
		}
	}
}