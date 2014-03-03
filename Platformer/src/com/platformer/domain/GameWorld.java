package com.platformer.domain;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

	private ArrayList<Entity> entities = null;
	private OrthogonalTiledMapRenderer renderer = null;
	
	public GameWorld()
	{
		entities = new ArrayList<Entity>();
	}
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Entity entity)
	{
		this.entities.add(entity);
	}
	public void removeEntity(Entity entity)
	{
		this.entities.remove(entity);
	}
	
	public void setRenderer(OrthogonalTiledMapRenderer rend) {
		
		this.renderer = rend;
	}
	public OrthogonalTiledMapRenderer getRenderer() {
		return this.renderer;
	}
	

}