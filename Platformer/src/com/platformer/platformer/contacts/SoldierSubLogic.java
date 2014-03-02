package com.platformer.platformer.contacts;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.platformer.domain.Soldier;

public class SoldierSubLogic extends ContactSubLogic {

	private int openContacts = 0;
	
	@Override
	public void endLogic(Contact contact) {
		
		
		Object contactA = contact.getFixtureA().getBody().getUserData();
		Object contactB = contact.getFixtureB().getBody().getUserData();		
				
		Soldier soldierObject =null;
		Fixture fixture = null;
		if(contactB instanceof Soldier) {
		
			soldierObject =(Soldier) contactB;
			fixture = contact.getFixtureB();
		}
		else if(contactA instanceof Soldier) {
		
			soldierObject = (Soldier) contactA;
			fixture = contact.getFixtureA();
		}
		
		if(soldierObject != null && fixture.getUserData() != null) {				

			openContacts--;
			if(openContacts > 0)  {
				soldierObject.setInTheSky(false);
			}
			else {
				soldierObject.setInTheSky(true);
			}
		}
		
		
	}

	@Override
	public void beginLogic(Contact contact) {
		
		Object contactA = contact.getFixtureA().getBody().getUserData();
		Object contactB = contact.getFixtureB().getBody().getUserData();
		
		Soldier soldierObject =null;
		Fixture fixture = null;
		if(contactB instanceof Soldier) {
			soldierObject =(Soldier) contactB;
			fixture = contact.getFixtureB();
		}
		else if(contactA instanceof Soldier) {
			soldierObject = (Soldier) contactA;
			fixture = contact.getFixtureA();
		}
		
		if(soldierObject != null) {
			if(fixture.getUserData()!= null) {
				soldierObject.setInTheSky(false);
				openContacts++;
			}
		}
	}

}
