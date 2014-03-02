package com.platformer.domain;

import java.util.ArrayList;

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
	
	public BulletContainer()
	{
		this.bullets = new ArrayList<Bullet>();
	}
	
}
