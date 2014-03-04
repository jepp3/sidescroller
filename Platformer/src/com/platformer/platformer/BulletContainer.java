package com.platformer.platformer;

import java.util.ArrayList;

import com.platformer.domain.Bullet;

/**
 * 
 * Contains bullets, and makes them to to disaper if they are to many
 * <br>
 * This class is used because we want to get better preformance, and move the logic from the shootable object 
 * @author jeppe
 *
 */
public class BulletContainer {
	private ArrayList<Bullet> bullets; 
	private int maxSize = -1;
	private final int cleanupIntervalMax = 30;
	private int cleanupIntervalCurrent = 0;
	public BulletContainer()
	{
		this.bullets = new ArrayList<Bullet>();
	}
	public BulletContainer(int maxSize)
	{
		this.bullets = new ArrayList<Bullet>();
		this.maxSize = maxSize;
	}
	
	public Bullet getBullet(float xPos, float yPos)
	{
		
		cleanUp();
		
		
		cleanupIntervalCurrent++;

		Bullet bullet = new Bullet(
				GlobalAccess.getGameWorldInstance(),
				PhysicWorld.getInstance(),
				xPos,
				yPos,
				0
			);
		
		bullets.add(bullet);
		GlobalAccess.getGameWorldInstance().addEntity(bullet);

		return bullet;
	}
	
	public void clearAllBullets(){
		//this.bullets.clear();
	}
	
	public void cleanUp()
	{
		if(cleanupIntervalCurrent == cleanupIntervalMax)
		{
			for (Bullet bullet : this.bullets) {
				
				GlobalAccess.getEntityDestroyerInstance().addToQueue(bullet);

			}
			cleanupIntervalCurrent = 0;
			bullets.clear();
		}
	}
}
