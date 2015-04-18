package com.jacklify85.ld32.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderUtils {

	private static SpriteBatch sBatch = new SpriteBatch();
	private static BitmapFont bDefaultFont = new BitmapFont();
	
	public void renderText(String msg, float x, float y) {
		bDefaultFont.draw(sBatch, msg, x, y);
	}
	
	public void renderText(BitmapFont font, String msg, float x, float y) {
		font.draw(sBatch, msg, x, y);
	}
	
	public void drawTexture(Texture texture, float x, float y) {
		sBatch.draw(texture, x, y);
	}
	
	public void beginRendering() {
		sBatch.begin();
	}
	
	public void stopRendering() {
		sBatch.end();
	}
}
