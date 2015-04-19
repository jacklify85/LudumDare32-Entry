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
	public static int score = 0;
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
			Zombie zombie = new Zombie(random.nextInt(5000) * random.nextFloat(), random.nextInt(1000) * random.nextFloat(), 22 + i);
		    GameScreen.world.addObject(zombie);
			this.box2d = new Box2DDebugRenderer();
		}
		
		for (int i = 0; i < 10; i++) {
			HealthPickup hPickup = new HealthPickup(random.nextInt(500), random.nextInt(300));
			GameScreen.world.addObject(hPickup);
		}
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		doInput();
		// Set projection matrix and render
		this.camera.position.set(GameScreen.player.getPosition(), this.camera.position.z);
		this.camera.update();
		RenderUtils.setMatrix(this.camera.combined);
		RenderUtils.beginRendering();
		//////////////////
		if (!this.isPaused) {
			//this.box2d.render(world.world, this.camera.combined);
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
		// 0 = UP, 1 = DOWN, -1 = LEFT, -2 = RIGHT
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			player.direction = 0;
			GameScreen.world.doMovement(player, player.getX(), player.getY() + 2.5f);
		}
		
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.direction = -1;
			GameScreen.world.doMovement(GameScreen.player, GameScreen.player.getX() - 2.5f, GameScreen.player.getY());
		}
		
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.direction = -2;
			GameScreen.world.doMovement(GameScreen.player, GameScreen.player.getX() + 2.5f, GameScreen.player.getY());
		}
		
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.direction = 1;
			GameScreen.world.doMovement(GameScreen.player, GameScreen.player.getX(), GameScreen.player.getY() - 2.5f);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			// Pause game or unpause
			this.isPaused = !this.isPaused;
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			player.weapon.use();            
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