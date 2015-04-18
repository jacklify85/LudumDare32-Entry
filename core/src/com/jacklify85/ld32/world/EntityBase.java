package com.jacklify85.ld32.world;

import com.badlogic.gdx.math.Vector2;

public abstract class EntityBase implements IGameObject{

	private Vector2 position;
	protected int health, maxHealth;
	private int id;
	private boolean isDead = false;
	
	public EntityBase(float x, float y, int health, int maxHealth, int id) {
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
	
	public int getHealth() {
		return this.health;
	}
	
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public void damage(int amount) {
		this.health -= amount;
	}
	
	public void heal (int amount) {
		this.health += amount;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMaxHealth(int mHealth) {
		this.maxHealth = mHealth;
	}
	
	public void die() {
		this.isDead = true;
		this.died();
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	protected abstract void died();
}
