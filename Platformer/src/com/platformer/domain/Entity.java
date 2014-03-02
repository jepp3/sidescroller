package com.platformer.domain;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Entity {


	protected float x, y, angle;
	Rectangle rectangle;
	protected Image image;
	public Entity(final GameWorld gameWorld, float x, float y, float angle)
	{
		this.x = x;
		this.y = y;
		this.angle = angle;
		rectangle = new Rectangle();
		//loadImage("koalio.png");
		//image = getImage();
		//image.setRotation(angle);
	}

	public abstract Image getImage();
	
	abstract public void draw(SpriteBatch spriteBatch,float delta);

	
	/**
	 * Sets the position of the entity 
	 * @param x
	 * @param y
	 */
	public abstract void setPos(float x, float y);
	
	/**
	 * Sets the angle (rotation) on the entity
	 * @param angle degrees
	 */
	public void setAngle(float angle)
	{
		this.angle = angle;
	}
	
	/**
	 * Loads the image that will be used by the entity
	 * @param name
	 * @return
	 */
	 protected static Texture loadImage(String name) {
			return new Texture(Gdx.files.internal(name));	 
	}	
	/**
	 * Paints the entity
	 * @param alfa
	 */
	/**
	 * Updates the entity
	 * @param delta
	 */
	public void update(float delta) {
	}
	
}
