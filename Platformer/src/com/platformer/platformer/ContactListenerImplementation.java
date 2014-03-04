package com.platformer.platformer;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.platformer.domain.Bullet;
import com.platformer.domain.EnemySoldier;
import com.platformer.domain.MortalDynamicPhysicsEntity;
import com.platformer.domain.Soldier;
import com.platformer.platformer.contacts.BulletHitAnything;
import com.platformer.platformer.contacts.BulletHitMortalSubLogic;
import com.platformer.platformer.contacts.ContactSubLogic;
import com.platformer.platformer.contacts.SoldierSubLogic;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

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
	
			Fixture contactA = contact.getFixtureA();
			Fixture contactB = contact.getFixtureB();
			Object objB = null;
			Object objA = null;
			
			if(contactA != null)
				objA = contactA.getBody().getUserData();
			
			
			if(contactB != null)
				objB = contactB.getBody().getUserData();

			
			if(objB instanceof Soldier || objA instanceof Soldier) {
				soldierSubLogic.endLogic(contact);
			}
			
		
	
	}

	@Override
	public void beginContact(Contact contact) {
	

		Object contactA = contact.getFixtureA().getBody().getUserData();
		Object contactB = contact.getFixtureB().getBody().getUserData();
	
		
		if(contactA instanceof EnemySoldier && contactB instanceof Bullet) {
			BulletHitMortalSubLogic bulletHitSubLogic = new BulletHitMortalSubLogic((Bullet)contactB, (MortalDynamicPhysicsEntity)contactA);
			bulletHitSubLogic.beginLogic();
		}
	
		if(contactB instanceof EnemySoldier && contactA instanceof Bullet) {
			BulletHitMortalSubLogic bulletHitSubLogic = new BulletHitMortalSubLogic((Bullet)contactA, (MortalDynamicPhysicsEntity)contactB);
			bulletHitSubLogic.beginLogic();
		}
		
		
		if(contactA instanceof Bullet)
		{
			BulletHitAnything bulletHitAnything = new BulletHitAnything();
			bulletHitAnything.beginLogic((Bullet)contactA);
		}
		if(contactB instanceof Bullet) {
			BulletHitAnything bulletHitAnything = new BulletHitAnything();
			bulletHitAnything.beginLogic((Bullet)contactB);
		}
		
	
		
		
		
		if(contactB instanceof Soldier || contactA instanceof Soldier) {
			soldierSubLogic.beginLogic(contact);
		}
	
	
	

	}
}
