package com.platformer.domain;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

	private Array<Entity> entities = null;
	private OrthogonalTiledMapRenderer renderer = null;
	
	public GameWorld()
	{
		entities = new Array<Entity>();
	}
	public Array<Entity> getEntities() {
		return entities;
	}
	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}
	
	public void addEntity(Entity entity)
	{
		this.entities.add(entity);
	}
	
	public void setRenderer(OrthogonalTiledMapRenderer rend) {
		
		this.renderer = rend;
	}
	public OrthogonalTiledMapRenderer getRenderer() {
		return this.renderer;
	}

}