package com.platformer.platformer.contacts;


import com.platformer.domain.Bullet;
import com.platformer.domain.MortalDynamicPhysicsEntity;
import com.platformer.platformer.GlobalAccess;

public class BulletHitSubLogic {

	
	private Bullet bullet;
	private MortalDynamicPhysicsEntity mortal;
	public BulletHitSubLogic(Bullet bullet, MortalDynamicPhysicsEntity mortal)
	{
		this.bullet = bullet;
		this.mortal = mortal;
	}



	public void beginLogic() {
		
		GlobalAccess.getEntityDestroyerInstance().addToQueue(mortal);
	}
}
