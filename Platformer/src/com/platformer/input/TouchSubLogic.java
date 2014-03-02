package com.platformer.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.platformer.domain.Movable;
import com.platformer.domain.Soldier;
import com.platformer.platformer.ContactListenerImplementation;


public class TouchSubLogic extends InputSublogic {
	
	//ContactListenerImplementation contactListener;
	public TouchSubLogic(Movable movable, ContactListener listner)
	{
		super(movable);
	//	contactListener = (ContactListenerImplementation) listner;
	}

	private boolean checkMultiTouch(float startX, float endX) {
		final int MAX_NUMBER_OF_FINGERS = 2;
		int numberOfFingersOnScreen = 0;
		while(Gdx.input.isTouched(numberOfFingersOnScreen) == true && numberOfFingersOnScreen < MAX_NUMBER_OF_FINGERS) {
			float pointX = Gdx.input.getX(numberOfFingersOnScreen)/(float) Gdx.graphics.getWidth();
			if(pointX >= startX && pointX <= endX) {
				return true;
			}
			numberOfFingersOnScreen++;
		}
		return false;
	}

	@Override
	protected boolean checkLeft() {
		return checkMultiTouch(0, 0.25f);
	}


	@Override
	protected boolean checkRight() {
		return checkMultiTouch(0.25f, 0.5f);
	}


	@Override
	protected boolean checkJump() {
		return ((Soldier)movable).isInTheSky()  == false && checkMultiTouch(0.75f, 1);
	}

	@Override
	protected boolean checkFly() {
		// not implemented, so the soldier is not flying
		return checkMultiTouch(0.55f, 0.74f);
	}
}
