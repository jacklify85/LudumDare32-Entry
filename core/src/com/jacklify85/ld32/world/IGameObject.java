package com.jacklify85.ld32.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface IGameObject {

	public void update();
	
	public void draw();

	public float getHeight();
	
	public float getWidth();
	
	public Body getBody();
	
	public void setBody(Body b);

	public Vector2 getPosition();
}
