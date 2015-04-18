package com.jacklify85.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.jacklify85.ld32.physics.Box2DContactManager;
import com.jacklify85.ld32.pickups.PickupBase;

public class GWorld implements Disposable {
	
	// BOX2D LOGIC
	private static final float STEP_TIME = 1/45f;
	private static final int VELOCITY_ITERATIONS = 4;
	private static final int POSITION_ITERATIONS = 5;
	
	public World world;
	private Array<Body> bodies = new Array<Body>();
	
	// BOX2D LIGHTS LOGIC
	//private RayHandler rHandler;

	public GWorld() {
		this.world = new World(new Vector2(0.0f, -9.0f), true);
		this.world.setContactListener(new Box2DContactManager());
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
						this.cleanupBody(body);
					}
				}
				eBase.draw();
			} else if (gObject instanceof PickupBase) {
				PickupBase pBase = (PickupBase)gObject;
				
				// check if pickup is used
				if (pBase.shouldDestroy()) {
					this.cleanupBody(body);
					continue;
				}
				// render pickup
				pBase.draw();
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
		bDef.position.set(object.getPosition());
		bDef.allowSleep = true;
		bDef.bullet = true;
		bDef.type = BodyType.StaticBody;
		if (object instanceof Player) {
			bDef.type = BodyType.DynamicBody;
		}
		bDef.gravityScale = 0.0f;
		FixtureDef fDef = new FixtureDef();
		fDef.isSensor = false;
		fDef.friction = 0.5f;
		fDef.density = 3.5f;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(object.getWidth() / 2, object.getHeight() / 2);
		fDef.shape = shape;
		Body b = this.world.createBody(bDef);
		b.createFixture(fDef);
		b.setUserData(object);
		object.setBody(b);
		// Dispose shape
		shape.dispose();
	}

	@Override
	public void dispose() {
		//this.rHandler.dispose();
		this.world.dispose();
	}
	
	public void cleanupBody(Body b) {
		this.world.destroyBody(b);
	}
}
