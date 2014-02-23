package com.platformer.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class EnemySoldier extends DynamicPhysicsEntity implements Movable{
	
	//private Animation stand;
	//private Animation walk;
	//private float  stateTime;
	private Soldier seekObject = null;
	private final int maxSpeed = 1;
//	private boolean facesRight = true;
    //private boolean idle = true;
    
    
    
	public EnemySoldier(GameWorld gameworld, World world, float  x , float y ,float angle) {
		super(gameworld,world,x,y,angle);
		loadSprite();
		//stateTime = 0;
	}
	
	
	public EnemySoldier(GameWorld gameworld, World world, float  x , float y ,float angle,Soldier seek) {
		super(gameworld,world,x,y,angle);
		loadSprite();
		this.seekObject = seek;
		//stateTime = 0;
	}
	@Override
	Body initPhysicsBody(World world, float x, float y, float angle) {
		
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("soldier.png")));
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
		Body box = world.createBody(def);

		PolygonShape poly = new PolygonShape();		
		box.setAwake(true);
		box.setActive(true);
		poly.setAsBox(0.45f, .5f);
		Fixture u = box.createFixture(poly, 2f);
		u.setDensity(3);
		poly.dispose();	
		PolygonShape feet = new PolygonShape();		
		box.setAwake(true);
		box.setActive(true);
		feet.setAsBox(0.42f, .01f,new Vector2(0,-0.50f),0);
		Fixture f = box.createFixture(feet, 2f);
		f.setUserData("feets");
		f.setRestitution(0.1f);
		f.setFriction(1);
		f.setDensity(0);
		feet.dispose();	
		box.setUserData(this);
		box.setSleepingAllowed(false);
		box.setFixedRotation(true);
	
		sprite.setSize(1, 1f);
		return box;
		
		
		//return null;
	}
	

	@Override
	public void moveRight() {
		if(getBody().getLinearVelocity().x <= 8) // speed check
			getBody().applyLinearImpulse(new Vector2(5.0f,0f), getBody().getWorldCenter(),true);
	}

	@Override
	public void moveLeft() {
		if(getBody().getLinearVelocity().x >= -8)
			getBody().applyLinearImpulse(new Vector2(-5.0f,0f), getBody().getWorldCenter(),true);
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void idle() {
		// TODO Auto-generated method stub
		
	}

	private void loadSprite() {
		
	}
	
	@Override
	public Image getImage() {
		return new Image(loadImage("soldier.png"));
	}
	
	
	public void seekThis(Soldier entity) {
		this.seekObject = entity;
	}
	private void artificialIntelligence() {
		
		if(this.seekObject != null && this.seekObject.isDead == false) {
			float direction = calculateDirectionToMove();
			determentAction(calculateDistance());
			getBody().applyLinearImpulse(new Vector2(direction,0f), getBody().getWorldCenter(),true);
		} else {
			// be idle
		}
	}
	private double calculateDistance() {
		Vector2 targetPos = this.seekObject.getBody().getPosition();
		Vector2 thisPos = this.getBody().getPosition();
		
		double distance = targetPos.x - thisPos.x;
		
		
		
		
		
		return Math.abs(distance);
		
	}
	private void determentAction(double distance) {
		
		System.out.println(distance);
			final double knifeDistance 	= 1;
			final double shootDistance 	= 5;
			final double runDistance 		= 10;
			
			if(distance < knifeDistance) {
				this.seekObject.die();
				
			} else if(distance < shootDistance) {
				
				
			} else if(distance < runDistance) {
				
				
			}
			
				
	}
	private int calculateDirectionToMove() {
		
		Vector2 targetPos = this.seekObject.getBody().getPosition();
		Vector2 thisPos = this.getBody().getPosition();
	
		if(targetPos.y  - 1.5 >  thisPos.y) {
			return 0;
		}
		if(targetPos.x < thisPos.x) {
			if(getBody().getLinearVelocity().x <= maxSpeed)
				return -2;
		}
		else{
			if(getBody().getLinearVelocity().x >= -maxSpeed)
				return +2;
		}
		return 0;
	}
	@Override
	public void draw(SpriteBatch spriteBatch, float delta) {
		this.artificialIntelligence();
		//stateTime += delta;
        TextureRegion frame = null;
       // frame = stand.getKeyFrame(stateTime);
		
//		if(this.idle == false) {
//			frame = walk.getKeyFrame(stateTime);  
//		}
		
//		if(facesRight) {
//			spriteBatch.draw(frame, getBody().getPosition().x - 0.5f, getBody().getPosition().y -(.5f), 1, 1);
	//	}
	//	else {
	//		spriteBatch.draw(frame, getBody().getPosition().x + 0.5f, getBody().getPosition().y -(.5f), -1, 1);
	//	}
		
	}


}
