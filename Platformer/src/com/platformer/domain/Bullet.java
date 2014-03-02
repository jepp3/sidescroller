package com.platformer.domain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bullet extends StaticEntity {

	public Bullet(GameWorld gameWorld, World world, float x, float y,
			float angle) {
		super(gameWorld, world, x, y, angle);
		// TODO Auto-generated constructor stub
	}

	@Override
	Body initPhysicsBody(World world2, float x, float y, float angle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPos(float x, float y) {
		// TODO Auto-generated method stub
		
	}

}
