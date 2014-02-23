package com.platformer.input;

import com.platformer.domain.Movable;

public abstract class InputSublogic {
	/**
	 * Implements the specific logic that is unique for the current input type 
	 */
	protected Movable movable;
	
	public InputSublogic(Movable movableObject)
	{
		movable = movableObject;
	}
	
	
	public void process()
	{
		boolean idle = true;
		 if(checkLeft()) {
			 	movable.moveLeft();
		 		idle = false;
		 }
		 if(checkRight()) {
			 	movable.moveRight();
		 		idle = false;
		 }
		 if(checkJump()) {
			 	movable.jump();
			 	idle = false;
		 }
		 if(checkFly()) {
			 	movable.fly();
			 	idle = false;
		 }		 	 
		 if(idle)
		 {
			 movable.idle();
		 }	
	}
	
	
	protected abstract boolean checkLeft();
	protected abstract boolean checkRight();
	protected abstract boolean checkJump();
	protected abstract boolean checkFly();
	
}
