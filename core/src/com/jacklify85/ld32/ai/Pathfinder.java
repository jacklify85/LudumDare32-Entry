package com.jacklify85.ld32.ai;

import com.jacklify85.ld32.world.EntityBase;

public class Pathfinder {

	private EntityBase host;
	private EntityBase target;
	
	public Pathfinder(EntityBase host, EntityBase target) {
		this.host = host;
		this.target = target;
	}
	
	public float getMovementX() {
		return this.target.getPosition().x - this.host.getPosition().x;
	}
	
	public float getMovementY() {
		return this.target.getPosition().y - this.host.getPosition().y;
	}

}
