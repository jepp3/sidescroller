package com.platformer.domain;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Box extends DynamicPhysicsEntity {
	
	public Box(GameWorld gameWorld, World world, float x, float y, float angle) {
		super(gameWorld, world, x, y, angle);
		
		image = getImage();
		image.setRotation(angle);
		image.setSize(1,1);
	}

	@Override
	Body initPhysicsBody(World world, float x, float y, float angle) {
			
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		Body box = world.createBody(def);

		PolygonShape poly = new PolygonShape();		
		poly.setAsBox(0.5f, 0.5f);
		Fixture f = box.createFixture(poly, 2f);
		f.setDensity(400);
		f.setFriction(1);
		box.setAwake(true);
		box.setActive(true);
		
		return box;
		
	}
	
	@Override
	public Image getImage() {
		return new Image(loadImage("box.png"));
	}
}