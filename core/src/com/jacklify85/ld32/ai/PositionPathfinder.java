package com.jacklify85.ld32.ai;

import com.jacklify85.ld32.world.EntityBase;

public class PositionPathfinder {

	private EntityBase host;
	private float targetX, targetY;
	
	public PositionPathfinder(EntityBase host, float targetX, float targetY) {
		this.host = host;
		this.targetX = targetX;
		this.targetY = targetY;
	}
	
	public float getMovementX() {
		return this.targetX - this.host.getPosition().x;
	}
	
	public float getMovementY() {
		return this.targetY - this.host.getPosition().y;
	}
}
