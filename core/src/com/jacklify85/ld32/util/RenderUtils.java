package com.jacklify85.ld32.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class RenderUtils {

	private static SpriteBatch sBatch = new SpriteBatch();
	private static BitmapFont bDefaultFont = new BitmapFont();
	
	public static void renderText(String msg, float x, float y) {
		bDefaultFont.draw(sBatch, msg, x, y);
	}
	
	public static void renderText(BitmapFont font, String msg, float x, float y) {
		font.draw(sBatch, msg, x, y);
	}
	
	public static void drawTexture(Texture texture, float x, float y) {
		sBatch.draw(texture, x, y);
	}
	
	public static void beginRendering() {
		sBatch.begin();
	}
	
	public static void stopRendering() {
		sBatch.end();
	}
	
	public static void setMatrix(Matrix4 projection) {
		sBatch.setProjectionMatrix(projection);
	}

	public static BitmapFont getFont() {
		return bDefaultFont;
	}
	
	public static void endGameScreen(int score) {
		bDefaultFont.setColor(Color.ORANGE);
		bDefaultFont.draw(sBatch, "You've been eaten! Your score was: " + score + " Thanks for playing!", 100, 100);
		bDefaultFont.draw(sBatch, "Press 'ENTER' to start a NEW GAME!", 100, 80);
		bDefaultFont.setColor(Color.WHITE);
	}
}
