package com.jacklify85.ld32.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jacklify85.ld32.util.RenderUtils;
import com.jacklify85.ld32.world.GWorld;
import com.jacklify85.ld32.world.Player;

public class GameScreen implements Screen{

	private Camera camera = null;
	private GWorld world;
	private Player player;
	private boolean isPaused = false;
	private int score = 0;
	public static volatile boolean alive = true;
	
	public GameScreen() {
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.world = new GWorld();
		this.player = new Player(159, 122, 21);
		this.world.addObject(player);
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
			this.world.render();
		} else {
			RenderUtils.renderText("Game is paused! To resume press escape!", 50, 25);
		}
		/////////////////
		RenderUtils.stopRendering();
	}

	private void doInput() {
		// TODO: IMPLEMENT MOBILE TOUCH SCREEN FOR ANDROID
		if (this.player == null) {
			return;
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			this.world.doMovement(this.player, this.player.getX(), this.player.getY() + 10.5f);
		}
		
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.world.doMovement(this.player, this.player.getX() - 10.5f, this.player.getY());
		}
		
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.world.doMovement(this.player, this.player.getX() + 10.5f, this.player.getY());
		}
		
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.world.doMovement(this.player, this.player.getX(), this.player.getY() - 10.5f);
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
		if (this.world != null) {
			this.world.dispose();
		}
	}
}