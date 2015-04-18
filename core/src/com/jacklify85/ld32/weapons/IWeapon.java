package com.jacklify85.ld32.weapons;

import com.sun.xml.internal.stream.Entity;

public interface IWeapon {

	public void draw();
	
	public void onHit(Entity target);
	
	public void use();
	
}
