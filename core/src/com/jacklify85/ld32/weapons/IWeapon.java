package com.jacklify85.ld32.weapons;

import com.jacklify85.ld32.world.EntityBase;

public interface IWeapon {

	public void draw();
	
	public void onHit(EntityBase target);
	
	public void use();

	void use(int xPos, int yPos);
	
}
