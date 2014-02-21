package com.platformer.domain;

import com.badlogic.gdx.physics.box2d.Body;


public interface PhysicsEntity {

	  public Body getBody();

	  public interface HasContactListener {
		    public void contact(PhysicsEntity other);
	  }
}