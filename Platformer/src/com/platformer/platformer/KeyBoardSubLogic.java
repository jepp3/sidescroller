package com.platformer.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.platformer.domain.Movable;

public class KeyBoardSubLogic extends InputSublogic {
	
	
	ContactListenerImplementation contactListener;
	public KeyBoardSubLogic(Movable movableObject,ContactListener listner) {
		super(movableObject);
		contactListener = (ContactListenerImplementation) listner;

	}

	@Override
	protected boolean checkLeft() {
		return Gdx.input.isKeyPressed(Keys.A);
	}

	@Override
	protected boolean checkRight() {
		return Gdx.input.isKeyPressed(Keys.D);
		
	}

	@Override
	protected boolean checkJump() {
		return contactListener.inTheSky == false && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	protected boolean checkFly() {
		return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);
	}

}
