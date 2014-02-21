package com.platformer.domain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class DynamicPhysicsEntity extends Entity implements PhysicsEntity {

	// for calculating interpolation
	  private float prevX, prevY, prevA;
	  private Body body;

	  /**
	   * Creates the DynamicPhysicsEntity, adds it to the worlds
	   * @param gameWorld
	   * @param x
	   * @param y
	   * @param angle
	   */
	  public DynamicPhysicsEntity(GameWorld gameWorld,World world, float x, float y, float angle) {
		  super(gameWorld, x, y, angle);
		  
		  body = initPhysicsBody(world, x, y, angle);
		  setPos(x, y);
		  setAngle(angle);
	  }
  
	 abstract Body initPhysicsBody(World world, float x, float y, float angle);


	 @Override
	  public void draw(SpriteBatch spriteBatch, float alpha) {
		 
		 
		image.setPosition(getBody().getPosition().x-image.getWidth()/2, getBody().getPosition().y-image.getHeight()/2);
		image.setOrigin(0.5f,0.5f); // set where to rotate the image 
		image.setRotation(getBody().getAngle() * MathUtils.radiansToDegrees);
		image.draw(spriteBatch,1);
	  }
	 
	 /**
	  * This method is not needed to be overriden, due to some entities might not need it
	  */
	  @Override
	  public void update(float delta) {

	  }

	  @Override
	  public Body getBody() {
		return body;
	  }
	  @Override
	  public Image getImage() {
		  return null;
	  }
	 
	  public void setLinearVelocity(float x, float y) {
		  body.setLinearVelocity(new Vector2(x, y));
	  }

	  public void setAngularVelocity(float w) {
		  body.setAngularVelocity(w);
	  }
	  @Override
	  public void setPos(float x, float y) {
		    super.setPos(x, y);
		    getBody().setTransform(new Vector2(x, y), getBody().getAngle());
		    prevX = x;
		    prevY = y;
	  }
	  
	  @Override
	  public void setAngle(float angle)
	  {
		    super.setAngle(angle);
		    getBody().setTransform(getBody().getPosition(), angle);
		    prevA = angle;
	  }
}
