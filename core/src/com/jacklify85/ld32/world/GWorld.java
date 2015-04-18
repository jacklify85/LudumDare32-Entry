package com.jacklify85.ld32.world;

import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class GWorld implements Disposable {
	
	// BOX2D LOGIC
	private static final float STEP_TIME = 1/45f;
	private static final int VELOCITY_ITERATIONS = 4;
	private static final int POSITION_ITERATIONS = 5;
	
	private World world;
	private Array<Body> bodies = new Array<Body>();
	
	// BOX2D LIGHTS LOGIC
	//private RayHandler rHandler;

	public GWorld() {
		this.world = new World(new Vector2(0.0f, 0.0f), true);
		//this.rHandler = new RayHandler(world);
		//RayHandler.useDiffuseLight(true);
	}
	
	public void render() {
		this.bodies.clear();
		this.world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
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
				eBase.setPosition(body.getPosition().x, body.getPosition().y);
				body.setActive(true);
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
	
	public void doMovement(EntityBase eBase, float x, float y) {
		if (eBase.getBody() == null) {
			Gdx.app.error("LDGAME", "Tried to update position for entity (ID = " + eBase.getId() + " ). No body found.");
			return;
		}
		eBase.getBody().setTransform(x, y, eBase.getBody().getAngle());
		eBase.getBody().setActive(true);
	}
	
	public void addObject(IGameObject object) {
		BodyDef bDef = new BodyDef();
		bDef.allowSleep = true;
		bDef.bullet = false;
		FixtureDef fDef = new FixtureDef();
		
		Body b = this.world.createBody(bDef);
		b.setUserData(object);
		object.setBody(b);
	}

	@Override
	public void dispose() {
		//this.rHandler.dispose();
		this.world.dispose();
	}
}
