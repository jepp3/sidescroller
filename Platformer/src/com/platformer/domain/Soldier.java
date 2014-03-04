package com.platformer.domain;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.platformer.platformer.BulletContainer;
import com.platformer.platformer.Constants;
import com.platformer.platformer.PhysicWorld;

public class Soldier extends MortalDynamicPhysicsEntity  implements Controllable{

	private static final float MOVE_SPEED = 0.1f; 
	
	private Animation stand;
	private Animation walk;
    float stateTime;
	private boolean facesRight = true;
    private boolean idle = true;
    private boolean inTheSky = false;
    private Inventory inventory = new Inventory();
	private Sound sound;

   //private BulletContainer bulletContainer = new BulletContainer();
    
	public Soldier(GameWorld gameWorld, World world, float x, float y,float angle) {
		super(gameWorld, world, x, y, angle);
		loadSprite();
		stateTime = 0;
		inventory.setCurrentWeapon(new StandardWeapon());
		sound = Gdx.audio.newSound(Gdx.files.internal("sound/gun-gunshot.mp3"));
	}
	
	private void loadSprite()
	{ 
        Texture koalaTexture = new Texture("koalio.png");
        
        TextureRegion[] regions = TextureRegion.split(koalaTexture, 64,64 )[0];
        stand = new Animation(0, regions[0]);
        walk = new Animation(0.15f, regions[2], regions[3], regions[4],regions[5],regions[6]);
        walk.setPlayMode(Animation.LOOP_PINGPONG);
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
		
	}
	@Override
	public void moveRight() {		
		if(!isDead()) {
		this.facesRight = true;
		this.idle =false;
		if(getBody().getLinearVelocity().x <= 13) // speed check
			getBody().applyLinearImpulse(new Vector2(5.0f,0f), getBody().getWorldCenter(),true);
		}
	}
	@Override
	public void moveLeft() {
		if(!isDead()) {
			this.facesRight = false;
			this.idle =false;
			if(getBody().getLinearVelocity().x >= -13)
				getBody().applyLinearImpulse(new Vector2(-5.0f,0f), getBody().getWorldCenter(),true);
		}
	}
	@Override
	public void fly() {
		if(!isDead()){
			getBody().applyLinearImpulse(0, 2f, getBody().getPosition().x, getBody().getPosition().y,true);
		}
	}
	@Override
	public void jump() {
		if(!isDead()) {
			getBody().applyLinearImpulse(new Vector2(0.0f,25f), getBody().getWorldCenter(),true);
		}
	}
	@Override
	public void idle() {
		this.idle = true;
	}
	@Override
	public Image getImage() {
		return new Image(loadImage("soldier.png"));
	}
	@Override
	public void draw(SpriteBatch spriteBatch, float delta) {
		stateTime += delta;
        TextureRegion frame = null;
        frame = stand.getKeyFrame(stateTime);
		
		if(this.idle == false) {
			frame = walk.getKeyFrame(stateTime);  
		}
		
		if(facesRight) {
			spriteBatch.draw(frame, getBody().getPosition().x - 0.5f, getBody().getPosition().y -(.5f), 1, 1);
		}
		else {
			spriteBatch.draw(frame, getBody().getPosition().x + 0.5f, getBody().getPosition().y -(.5f), -1, 1);
		}
    }
	
	@Override
	public  void die() {
		super.die();
	}

	public boolean isInTheSky() {
		return inTheSky;
	}
	
	public void setInTheSky(boolean inSky) {
		this.inTheSky = inSky;
	}
	@Override
	public void resurrect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		if(!isDead()) {
			Weapon weapon = this.inventory.getCurrentWeapon();
			Vector2 currentPos = this.getBody().getPosition();
			if(weapon != null)
			{
				sound.play(0.6f);
				if(facesRight) {
					weapon.shootRight(currentPos);
				}
				else {
					weapon.shootLeft(currentPos);
					
				}
			
			}
		}
	}

	@Override
	public void destroy() {
		Body bodyToDestroy = super.getBody();
		PhysicWorld.getInstance().destroyBody(bodyToDestroy);
		
	}
	
	
}
