package com.platformer.platformer;
import net.dermetfan.utils.libgdx.box2d.Box2DMapObjectParser;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.platformer.domain.Box;
import com.platformer.domain.Entity;
import com.platformer.domain.GameWorld;




public class WorldLoader {

	/**
	 * Returns a world that contains entities that are defined in level xml file
	 * @param level xml string containing all entities
	 */
	public static GameWorld createWorld(final String level)
	{
		Texture.setEnforcePotImages(false);
		//create an instance of the world,
		GameWorld gameWorld = new GameWorld();
	
		gameWorld.setRenderer(loadWorld(level,gameWorld));
		loadEntities(gameWorld);
		
		return gameWorld;
				
	}
	
	/**
	 *  Puts the entites defined in the level file in the world
	 * @param level path string containing all entities 
	 */
	public static OrthogonalTiledMapRenderer loadWorld(String level,GameWorld gameWorld)
	{
		TiledMap map = new TmxMapLoader().load(level);
       
		Box2DMapObjectParser parser = new Box2DMapObjectParser();
		parser.load(PhysicWorld.getInstance(), map);

		
        OrthogonalTiledMapRenderer  mapRenderer = null;
        mapRenderer = new OrthogonalTiledMapRenderer(map,parser.getUnitScale());
        
        return mapRenderer;
	}
	
	
	private static void loadEntities(GameWorld gameWorld)
	{
		
		Entity box = new Box(gameWorld, PhysicWorld.getInstance(), 10,10, 0);
		Entity box2 = new Box(gameWorld, PhysicWorld.getInstance(), 15,10, 0);
		Entity box3 = new Box(gameWorld, PhysicWorld.getInstance(), 17,10, 0);
		Entity box4 = new Box(gameWorld, PhysicWorld.getInstance(), 18,14, 0);
		Entity box5 = new Box(gameWorld, PhysicWorld.getInstance(), 19,14, 0);
		Entity box6 = new Box(gameWorld, PhysicWorld.getInstance(), 22,15, 0);
		Entity box7 = new Box(gameWorld, PhysicWorld.getInstance(), 24,17, 0);
		
		Entity box8 = new Box(gameWorld, PhysicWorld.getInstance(), 20,14, 0);
		Entity box9 = new Box(gameWorld, PhysicWorld.getInstance(), 24,14, 0);
		Entity box10 = new Box(gameWorld, PhysicWorld.getInstance(), 26,15, 0);
		Entity box11 = new Box(gameWorld, PhysicWorld.getInstance(), 27,17, 0);
		gameWorld.addEntity(box);
		gameWorld.addEntity(box2);
		gameWorld.addEntity(box3);
		gameWorld.addEntity(box4);
		gameWorld.addEntity(box5);
		gameWorld.addEntity(box6);
		gameWorld.addEntity(box7);
		gameWorld.addEntity(box8);
		gameWorld.addEntity(box9);
		gameWorld.addEntity(box10);
		gameWorld.addEntity(box11);
		
	}

}
