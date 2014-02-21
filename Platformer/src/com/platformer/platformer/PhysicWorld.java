package com.platformer.platformer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.platformer.domain.Soldier;

public class PhysicWorld {
	private static World world = null;
	
	public static World getInstance()
	{
		if(world == null)
		{
			world =  new World(new Vector2(0, -100), true);
			setContactListner();
		}
		return world;
	}
	
	
	private static void setContactListner()
	{
		
	}
	
}
