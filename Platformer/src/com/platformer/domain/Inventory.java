package com.platformer.domain;

import java.util.ArrayList;


/**
 * Contains all the stuff that a soldier has.
 * @author jeppe
 *
 */
public class Inventory {
	
	private Weapon currentWeapon = null;
	
	public void setCurrentWeapon(Weapon weapon)
	{
		this.currentWeapon = weapon;
	}
	public Weapon getCurrentWeapon() {
		return this.currentWeapon;
	}
}
