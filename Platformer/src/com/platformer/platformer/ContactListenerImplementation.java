package com.platformer.platformer;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.platformer.domain.Soldier;

public class ContactListenerImplementation implements ContactListener{
	public int openContacts = 0;
	public boolean inTheSky = true;
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		//System.out.println("presolv");
		
	}
	
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//System.out.println("postsolv");
		
	}
	
	@Override
	public void endContact(Contact contact) {
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
				inTheSky = false;
			}
			else {
				System.out.println("in the sky");
				inTheSky = true;
			}
		}
	}

	@Override
	public void beginContact(Contact contact) {
	
		Object contactA = contact.getFixtureA().getBody().getUserData();
		Object contactB = contact.getFixtureB().getBody().getUserData();
		
		System.out.println("begin contact");
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
			
				if(fixture.getUserData()!= null)
				{
					System.out.println("jump ok");
					inTheSky = false;
					openContacts++;
				}
		}
	}
}
