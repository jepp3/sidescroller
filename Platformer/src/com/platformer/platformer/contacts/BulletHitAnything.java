package com.platformer.platformer.contacts;

import com.platformer.domain.Bullet;
import com.platformer.platformer.GlobalAccess;

public class BulletHitAnything {

	public void beginLogic(Bullet contactA) {
		// TODO Auto-generated method stub
		
		GlobalAccess.getEntityDestroyerInstance().addToQueue(contactA);
	}

}
