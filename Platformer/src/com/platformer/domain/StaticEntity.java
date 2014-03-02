package com.platformer.domain;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class StaticEntity extends Entity {

	private Body body;
	public StaticEntity(GameWorld gameWorld,World world, float x, float y, float angle) {
		super(gameWorld, x, y, angle);
		body = initPhysicsBody(world, x, y, angle);
		setPos(x,y);
		setAngle(angle);
	}

	

	public Body getBody()
	{
		return body;
	}

	abstract Body initPhysicsBody( World world2, float x, float y,
			float angle);
	
}
