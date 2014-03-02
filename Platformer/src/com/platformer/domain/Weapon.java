package com.platformer.domain;

public abstract class Weapon {
	
	private int capacity;
	private int damage;
	private BulletContainer bullets;
	private int ammoLeft;
	public Weapon() {
		capacity = 0;
		damage = 0;
		ammoLeft = 0;
	}
	
	public Weapon(int capacity,int damage) {
		this.capacity 	= capacity;
		this.damage 	= damage;
		this.ammoLeft 	= capacity;
	}
	
	public void shoot() {
		this.ammoLeft--;
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
