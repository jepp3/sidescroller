package com.platformer.platformer;

import com.platformer.domain.GameWorld;
import com.platformer.domain.Soldier;

public class GlobalAccess {
	
	private static Soldier soldier;
	private static GameWorld gameWorld;
	public static Soldier getSoldierInstance() {
	
		return GlobalAccess.soldier;
	
	}
	public static void setSoldier(Soldier soldier) {
		GlobalAccess.soldier = soldier;
	}
	public static void setGameWorld(GameWorld gameWorld) {
		GlobalAccess.gameWorld = gameWorld;
	}
	public static GameWorld getGameWorldInstance() {
		return gameWorld;
	}
	
}
