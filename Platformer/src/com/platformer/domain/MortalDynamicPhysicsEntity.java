package com.platformer.domain;

import com.badlogic.gdx.physics.box2d.World;

public abstract class MortalDynamicPhysicsEntity  extends DynamicPhysicsEntity  {
	
	protected int health;
	protected boolean isDead;
	protected Inventory inventory;
	
	
	public MortalDynamicPhysicsEntity(GameWorld gameWorld, World world,
			float x, float y, float angle) {
		super(gameWorld, world, x, y, angle);
		inventory = new Inventory();
	}
	
	public int getHealth()
	{
		return this.health;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	
	public  void die() {
		isDead = true;
	}
	public abstract void resurrect();
}
