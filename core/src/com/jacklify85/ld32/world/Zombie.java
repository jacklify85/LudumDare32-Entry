package com.jacklify85.ld32.world;

import com.jacklify85.ld32.ai.Pathfinder;

public class Zombie extends EntityBase{

	private Pathfinder pFinder = null;
	
	public Zombie(float x, float y, int health, int maxHealth, int id) {
		super(x, y, health, maxHealth, id);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void died() {
		// TODO Auto-generated method stub
		
	}

}
