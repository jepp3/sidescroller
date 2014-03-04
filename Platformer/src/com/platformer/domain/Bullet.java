package com.platformer.domain;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.platformer.platformer.PhysicWorld;


public class Bullet extends DynamicPhysicsEntity {

	CircleShape circle;
	
	public Bullet(GameWorld gameWorld, World world, float x, float y,
			float angle) {
		super(gameWorld, world, x, y, angle);
		
		image = getImage();
		image.setRotation(0);
		image.setSize(0.1f,0.1f);
	}
	
	public void fire(Vector2 somePositions) {
		this.getBody().applyLinearImpulse(somePositions, getBody().getWorldCenter(),true);
	}
	
	@Override
	Body initPhysicsBody(World world, float x, float y, float angle) {
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		Body body = world.createBody(def);
		body.setBullet(true);
		body.setAwake(true);
		
		circle = new CircleShape();
		circle.setRadius(0.03f);
		
		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 8f; 
		fixtureDef.friction = 0f;
		fixtureDef.restitution = 0f;
		body.setUserData(this);

		body.createFixture(fixtureDef);	
		this.circle.dispose();
		
		return body;
		
	}
	
	@Override
	public Image getImage() {
		return new Image(loadImage("bullet.png"));
	}
	

	
	public void destroy()
	{
		
		Body bodyToDestroy = super.getBody();
		Array<Fixture> fixtureList = bodyToDestroy.getFixtureList();
		if(bodyToDestroy!= null && fixtureList.size == 1) {
			
				
				PhysicWorld.getInstance().destroyBody(bodyToDestroy);
		}
			
	}
}
