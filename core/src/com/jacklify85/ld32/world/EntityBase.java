package com.jacklify85.ld32.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class EntityBase implements IGameObject{

	private Vector2 position;
	protected float health, maxHealth;
	private int id;
	private boolean isDead = false;
	private Body body = null;
	
	public EntityBase(float x, float y, float health, float maxHealth, int id) {
		this.position = new Vector2(x, y);
		this.health = health;
		this.maxHealth = maxHealth;
		this.id = id;
	}

	public float getX() {
		return this.position.x;
	}
	
	public float getY() {
		return this.position.y;
	}
	
	public void setX(float x) {
		this.position.set(x, position.y);
	}
	
	public void setY(float y) {
		this.position.set(position.x, y);
	}
	
	public void setPosition(float x, float y) {
		this.position.set(x, y);
	}
	
	public int getId() {
		return this.id;
	}
	
	public float getHealth() {
		return this.health;
	}
	
	public float getMaxHealth() {
		return this.maxHealth;
	}
	
	public void damage(float amount) {
		this.health -= amount;
	}
	
	public void heal (float amount) {
		this.health += amount;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public void setMaxHealth(float mHealth) {
		this.maxHealth = mHealth;
	}
	
	public void die() {
		this.isDead = true;
		this.died();
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	
	
	@Override
	public Body getBody() {
		return this.body;
	}

	@Override
	public void setBody(Body b) {
		this.body = b;
	}

	protected abstract void died();

	@Override
	public Vector2 getPosition() {
		return this.position;
	}
}
