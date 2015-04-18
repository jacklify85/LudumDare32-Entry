package com.jacklify85.ld32.pickups;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jacklify85.ld32.world.EntityBase;
import com.jacklify85.ld32.world.IGameObject;

public abstract class PickupBase implements IGameObject {

	private Vector2 position;
	private Body body;
	private boolean shouldDestroy = false;
	private int id;
	
	public PickupBase(float x, float y, int id) {
		this.position = new Vector2(x, y);
		this.id = id;
	}
	protected abstract void onUse(EntityBase e);

	public void use(EntityBase e) {
		this.shouldDestroy = true;
		this.onUse(e);
	}
	
	public boolean shouldDestroy() {
		return this.shouldDestroy;
	}
	
	@Override
	public void update() {
		// Stub
	}

	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void setBody(Body b) {
		this.body = b;
	}
	
	@Override
	public Vector2 getPosition() {
		return this.position;
	}
}
