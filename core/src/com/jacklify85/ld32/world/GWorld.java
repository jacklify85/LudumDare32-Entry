package com.jacklify85.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class GWorld {
	private World world;
	private Array<Body> bodies = new Array<Body>();
	
	public GWorld() {
		this.world = new World(new Vector2(0.0f, 0.0f), true);
	}
	
	public void render() {
		this.bodies.clear();
		this.world.getBodies(bodies);
		
		for (int index = 0; index < this.bodies.size; index++) {
			Body body = this.bodies.get(index);
			if (body.getUserData() == null) {
				Gdx.app.error("LDGAME", "Body (INDEX =  " + index + ")'s USERDATA IS NULL. ");
			}
			IGameObject gObject = (IGameObject)body.getUserData();
			// Determine object's implementation
			if (gObject instanceof EntityBase) {
				EntityBase eBase = (EntityBase)gObject;
				
				// simulate entity
				eBase.update();
				
				// entity is dead
				if (eBase.health <= 0) {
					if (eBase.isDead() == false) {
						// entity died, start cleanup.
						eBase.die();
					} else {
						// TODO: IMPLEMENT CLEANUP LOGIC
					}
				}
				eBase.draw();
			}
		}
	}
}
