package com.platformer.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.platformer.domain.Movable;
import com.platformer.domain.Soldier;
import com.platformer.platformer.ContactListenerImplementation;

public class KeyBoardSubLogic extends InputSublogic {
	
	
	//ContactListenerImplementation contactListener;
	public KeyBoardSubLogic(Soldier movableObject,ContactListener listner) {
		super(movableObject);
	//	contactListener = (ContactListenerImplementation) listner;

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
		return ((Soldier)movable).isInTheSky() == false && Gdx.input.isKeyPressed(Keys.SPACE);
	}

	@Override
	protected boolean checkFly() {
		return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);
	}

}
