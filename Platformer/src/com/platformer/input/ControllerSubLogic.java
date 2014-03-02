package com.platformer.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.utils.Array;
import com.platformer.domain.Movable;
import com.platformer.domain.Soldier;
import com.platformer.platformer.ContactListenerImplementation;

public class ControllerSubLogic extends InputSublogic{
	
	

	//private ContactListenerImplementation 	contactListener;
	Controller activeController;
	public ControllerSubLogic(Movable movable, ContactListener listner, Controller controller)
	{
		super(movable);
		
	//	contactListener =(ContactListenerImplementation) listner;
		activeController = controller;
	}	

	@Override
	protected boolean checkLeft() {
		return activeController.getPov(0) == PovDirection.west;
		
	}

	@Override
	protected boolean checkRight() {
		return activeController.getPov(0) == PovDirection.east;
	}

	@Override
	protected boolean checkJump() {
		return ((Soldier)movable).isInTheSky() == false && (activeController.getButton(0));
	}

	@Override
	protected boolean checkFly() {
		// not implemented
		return activeController.getButton(3);
	}

}
