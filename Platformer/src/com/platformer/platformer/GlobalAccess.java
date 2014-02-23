package com.platformer.platformer;

import com.platformer.domain.Soldier;

public class GlobalAccess {
	
	private static Soldier soldier;
	
	public static Soldier getSoldierInstance() {
	
			return GlobalAccess.soldier;
	
	}
	public static void setSoldier(Soldier soldier) {
		GlobalAccess.soldier = soldier;
	}
	
}
