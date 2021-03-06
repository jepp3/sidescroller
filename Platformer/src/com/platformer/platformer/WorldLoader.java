package com.platformer.platformer;
import net.dermetfan.utils.libgdx.box2d.Box2DMapObjectParser;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.platformer.domain.Box;
import com.platformer.domain.Bullet;
import com.platformer.domain.EnemySoldier;
import com.platformer.domain.Entity;
import com.platformer.domain.GameWorld;
import com.platformer.domain.Soldier;




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
		Entity box8 = new Box(gameWorld, PhysicWorld.getInstance(), 26,10, 0);
		
		
		gameWorld.addEntity(box);
		gameWorld.addEntity(box2);
		gameWorld.addEntity(box3);
		gameWorld.addEntity(box4);
		gameWorld.addEntity(box5);
		gameWorld.addEntity(box6);
		gameWorld.addEntity(box7);
		gameWorld.addEntity(box8);

		Entity bullet = new Bullet(gameWorld, PhysicWorld.getInstance(), 20, 8, 0);
		Entity bullet2 = new Bullet(gameWorld, PhysicWorld.getInstance(), 20, 12, 0);
		Entity bullet3 = new Bullet(gameWorld, PhysicWorld.getInstance(), 22, 14, 0);
		Entity bullet4 = new Bullet(gameWorld, PhysicWorld.getInstance(), 18, 16, 0);
		Entity bullet5 = new Bullet(gameWorld, PhysicWorld.getInstance(), 19, 12, 0);
		Entity bullet6 = new Bullet(gameWorld, PhysicWorld.getInstance(), 21, 12, 0);
		gameWorld.addEntity(bullet);
		gameWorld.addEntity(bullet2);
		gameWorld.addEntity(bullet3);
		gameWorld.addEntity(bullet4);
		gameWorld.addEntity(bullet5);
		gameWorld.addEntity(bullet6);
		Soldier soldier = new Soldier(gameWorld, PhysicWorld.getInstance(), 10,14, 0);
		GlobalAccess.setSoldier(soldier);
		gameWorld.addEntity(soldier);
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 20, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 40, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 60, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 80, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 20, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 40, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 60, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 80, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 20, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 40, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 60, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 80, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 20, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 40, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 60, 14, 0,GlobalAccess.getSoldierInstance()));
		gameWorld.addEntity(new EnemySoldier(gameWorld, PhysicWorld.getInstance(), 80, 14, 0,GlobalAccess.getSoldierInstance()));
	
	}

}
