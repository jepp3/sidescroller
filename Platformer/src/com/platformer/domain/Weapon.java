package com.platformer.domain;

import com.badlogic.gdx.math.Vector2;
import com.platformer.platformer.BulletContainer;

public abstract class Weapon {
	
	private int capacity;
	private int damage;
	private BulletContainer bullets;
	private int ammoLeft;
	public Weapon() {
		
		bullets = new BulletContainer();
		capacity = 0;
		damage = 0;
		ammoLeft = 0;
	}
	
	public Weapon(int capacity,int damage) {
		this.capacity 	= capacity;
		this.damage 	= damage;
		this.ammoLeft 	= capacity;
	}
	
	public void shootUp() {
		this.shoot();
		
	//	Vector2 pos = this.getBody().getPosition();
	//	Bullet bullet = this.bulletContainer.getBullet(pos.x,pos.y);
		
	//	bullet.fire();
	}
	
	private void shoot(){
		this.ammoLeft--;
	}
	public void shootLeft(Vector2 hostPosition) {
		this.shoot();
		System.out.println("left");
		Bullet bullet = this.bullets.getBullet(hostPosition.x-0.7f,hostPosition.y);
		Vector2 direction = new Vector2(-5f,0f);
		bullet.fire(direction);
	}
	public void shootRight(Vector2 hostPosition)
	{
		this.shoot();
		System.out.println("right");
		Bullet bullet = this.bullets.getBullet(hostPosition.x+0.7f,hostPosition.y);
		
		Vector2 direction = new Vector2(2f,0f);
		bullet.fire(direction);
	}
	public void reload() {
		this.ammoLeft = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAmmoLeft() {
		return ammoLeft;
	}

	public void setAmmoLeft(int ammoLeft) {
		this.ammoLeft = ammoLeft;
	}
}
