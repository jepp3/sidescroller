package com.platformer.platformer;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.platformer.domain.Soldier;
import com.platformer.platformer.contacts.ContactSubLogic;
import com.platformer.platformer.contacts.SoldierSubLogic;

public class ContactListenerImplementation implements ContactListener{

	private ContactSubLogic soldierSubLogic = new SoldierSubLogic();
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
		
	}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
		
	}
	
	@Override
	public void endContact(Contact contact) {
		Object contactA = contact.getFixtureA().getBody().getUserData();
		Object contactB = contact.getFixtureB().getBody().getUserData();
		
		if(contactB instanceof Soldier || contactA instanceof Soldier) {
			soldierSubLogic.endLogic(contact);
		}
	}

	@Override
	public void beginContact(Contact contact) {
	
		Object contactA = contact.getFixtureA().getBody().getUserData();
		Object contactB = contact.getFixtureB().getBody().getUserData();
		
		if(contactB instanceof Soldier || contactA instanceof Soldier) {
			soldierSubLogic.beginLogic(contact);
		}
	}
}
