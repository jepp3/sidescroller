package com.platformer.domain;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.platformer.platformer.GlobalAccess;
import com.platformer.platformer.PhysicWorld;


public class EnemySoldier extends MortalDynamicPhysicsEntity implements Controllable{
	
	private Animation stand;
	private Animation walk;
	private float  stateTime;
	private Soldier seekObject = null;
	private final int maxSpeed = 1;
	private boolean facesRight = false;
    private boolean idle = true;
    private boolean okToShoot = true;
    float delay = 1; // seconds
    private Sound sound;
    private Sound knifeSound;
    Timer timer = new Timer();
    
	public EnemySoldier(GameWorld gameworld, World world, float  x , float y ,float angle) {
		super(gameworld,world,x,y,angle);
		loadSprite();
		inventory.setCurrentWeapon(new StandardWeapon());
		sound = Gdx.audio.newSound(Gdx.files.internal("sound/gun-gunshot.mp3"));
		knifeSound = Gdx.audio.newSound(Gdx.files.internal("sound/knife.mp3"));
	}
	public EnemySoldier(GameWorld gameworld, World world, float  x , float y ,float angle,Soldier seek) {
		super(gameworld,world,x,y,angle);
		loadSprite();
		this.seekObject = seek;
		//stateTime = 0;
		inventory.setCurrentWeapon(new StandardWeapon());
		sound = Gdx.audio.newSound(Gdx.files.internal("sound/gun-gunshot.mp3"));
		knifeSound = Gdx.audio.newSound(Gdx.files.internal("sound/knife.mp3"));
	}
	@Override
	Body initPhysicsBody(World world, float x, float y, float angle) {
		
	//	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("soldier.png")));
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
	
	//	sprite.setSize(1, 1f);
	
		
		
		return box;
	}
	

	@Override
	public void moveRight() {
		facesRight = true;
		this.idle = false;
		if(getBody().getLinearVelocity().x <= 8) // speed check
			getBody().applyLinearImpulse(new Vector2(5.0f,0f), getBody().getWorldCenter(),true);
	}

	@Override
	public void moveLeft() {
		facesRight = false;
		this.idle = false;
		if(getBody().getLinearVelocity().x >= -8)
			getBody().applyLinearImpulse(new Vector2(-5.0f,0f), getBody().getWorldCenter(),true);
	}

	@Override// TODO Auto-generated method stub
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
		this.idle = true;
	}

	private void loadSprite() {
        Texture koalaTexture = new Texture("koalio.png");
        
        TextureRegion[] regions = TextureRegion.split(koalaTexture, 64,64 )[0];
        stand = new Animation(0, regions[0]);
        walk = new Animation(0.15f, regions[2], regions[3], regions[4],regions[5],regions[6]);
        walk.setPlayMode(Animation.LOOP_PINGPONG);
	}
	public void seekThis(Soldier entity) {
		this.seekObject = entity;
	}
	private void artificialIntelligence() {
		
		if(this.seekObject != null && this.seekObject.isDead == false) {
			calculateMovement();
			determentAction(calculateDistance());
		}
	}
	private Vector2 calculateDistance() {
		Vector2 targetPos = this.seekObject.getBody().getPosition();
		Vector2 thisPos = this.getBody().getPosition();
		
		double distanceX = targetPos.x - thisPos.x;
		Vector2 dist = new Vector2((float)Math.abs(distanceX),targetPos.y);
		return dist;
		
	}
	private void determentAction(Vector2 distance) {
		
			final double knifeDistance 	= 1;
			final double minShootDistance 	= 2;
			final double maxShootDistance 	= 7;
			final double runDistance 		= 10;
			
			if(((int)distance.y) == ((int)this.getBody().getPosition().y)) {
				if(distance.x < knifeDistance) {
					
					// set animation to knife
					this.knife();
					this.seekObject.die();
					
				} else if(distance.x > minShootDistance && distance.x < maxShootDistance) {
					// set animation to shoot
					initiateShooting();
				} else if(distance.x < runDistance) {
					
				
				}
			}
			else {
				// set animation to idle
			}
			
				
	}
	private void knife()
	{
		if(!this.seekObject.isDead())
			this.knifeSound.play();
	}
	private void calculateMovement() {
		
		Vector2 targetPos = this.seekObject.getBody().getPosition();
		Vector2 thisPos = this.getBody().getPosition();
	
		if(targetPos.y  - 1.5 >  thisPos.y || targetPos.y + 1.5 < thisPos.y) {
			this.idle();
		}
		else if(targetPos.x < thisPos.x) {
			
			this.moveLeft();
		}
		else{
			this.moveRight();
		}
		
	}
	private void initiateShooting() {
		if(okToShoot)
		{
			timer.scheduleTask(new Task() {
				@Override
				public void run() {
					shoot();
					okToShoot = true;
				}
			},0.5f);
		}
		okToShoot = false;
	}
	@Override
	public void draw(SpriteBatch spriteBatch, float delta) {
		this.artificialIntelligence();
		//stateTime += delta;
        TextureRegion frame = null;
        stateTime += delta;
       // TextureRegion frame = null;
        
        if(idle == true) {
        	frame = stand.getKeyFrame(stateTime);
        }
        else {
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
	public void shoot() {
		
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
	@Override
	public void resurrect() {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void die() {
		Random rand = new Random();

		int n = rand.nextInt(2) + 0;
		String[] screams = {"sound/scream.mp3","sound/scream2.mp3"};
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(screams[n]));
		sound.play(0.8f);
	}
	@Override
	public void destroy() {
		die();
		Body bodyToDestroy = super.getBody();
		
	 
		PhysicWorld.getInstance().destroyBody(bodyToDestroy);
		
	//	GlobalAccess.getGameWorldInstance().removeEntity(this);		
	}
}
