package com.platformer.platformer;

import java.util.ArrayList;

import com.platformer.domain.Entity;

public class EntityDestroyer {
	private  ArrayList<Entity> entites = new ArrayList<Entity>();
	
	public  void destroy() {
		for (Entity ent : entites) {
			GlobalAccess.getGameWorldInstance().removeEntity(ent);
			ent.destroy();
		}
		entites.clear();
	}
	
	public  void addToQueue(Entity entity)
	{
		entites.add(entity);
	}

}
