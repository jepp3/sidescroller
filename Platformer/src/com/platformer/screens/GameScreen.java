package com.platformer.screens;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.platformer.domain.*;
import com.platformer.input.ControllerSubLogic;
import com.platformer.input.InputLogicWraper;
import com.platformer.input.InputSublogic;
import com.platformer.input.KeyBoardSubLogic;
import com.platformer.input.TouchSubLogic;
import com.platformer.platformer.ContactListenerImplementation;
import com.platformer.platformer.GlobalAccess;
import com.platformer.platformer.PhysicWorld;
import com.platformer.platformer.Platformer;
import com.platformer.platformer.SoundLoader;
import com.platformer.platformer.WorldLoader;


/**
 * This is the screen for the actual game, it is the business logic for the game. 
 * 
 * This invokes methods for setting up the camera, load the tiles ,load the sounds
 * and update all the entities in the game. It is also responsible for user input.
 * 
 * @author jeppe
 *
 */
public class GameScreen implements Screen{
	
	
	private Box2DDebugRenderer box2DRenderer;
	private OrthographicCamera camera;
	private Platformer game;
    private GameWorld gameWorld;
	
	private InputLogicWraper input = new InputLogicWraper();
	
		
	//private boolean inTheSky = true;
	
	public GameScreen(Platformer game) {	
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10*1.4f, 6*1.4f);
		box2DRenderer = new  Box2DDebugRenderer();
        gameWorld = WorldLoader.createWorld("map/Box2DMapObjectParserTutorial.tmx");
        SoundLoader.loadSounds();
        
        
        try {
			desideInputMethod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
	}
	
	public void desideInputMethod() throws Exception
	{
		// search for controllers
		
		Controller controller = searchForController();
		ContactListener contactListener = new ContactListenerImplementation();
		PhysicWorld.getInstance().setContactListener(contactListener);
		Soldier soldier = GlobalAccess.getSoldierInstance();
		if(controller != null) {
	        
			InputSublogic subLogicController = new ControllerSubLogic(soldier,contactListener,controller);
	        input.addSubLogic(subLogicController);
		}

		InputSublogic subLogicTouch = new TouchSubLogic(soldier,contactListener);
		InputSublogic subLogicKeyBoard = new KeyBoardSubLogic(soldier,contactListener);
        
        
		input.addSubLogic(subLogicKeyBoard);
		input.addSubLogic(subLogicTouch);
		
		
		input.setActiveInput(0);
		
	}
	
	private  Controller searchForController()
	{
		Controller controller = null;
		for(Controller c : Controllers.getControllers())
		{
			controller = c;
		}
		
		return controller;
	}
	
		@Override
	public void show() {
		

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		PhysicWorld.getInstance().step(1/200f, 8, 3);
		
		SpriteBatch batch = (SpriteBatch) gameWorld.getRenderer().getSpriteBatch();
		
		Soldier soldier = GlobalAccess.getSoldierInstance();
		
		input.process();
		updateCameraLocation(soldier);
        gameWorld.getRenderer().setView(camera);
        gameWorld.getRenderer().render();
        gameWorld.getRenderer().getSpriteBatch().begin();
        soldier.draw(batch,delta);    
        
        for (Entity ent : gameWorld.getEntities()) {
        	ent.draw(batch,delta);
		}    
        gameWorld.getRenderer().getSpriteBatch().end();
     //   box2DRenderer.render(PhysicWorld.getInstance(), camera.combined);
	}
	

	private void updateCameraLocation(Soldier soldier) {

		camera.position.x=soldier.getBody().getPosition().x;
		camera.position.y=soldier.getBody().getPosition().y;
		
		camera.update();
	}
	@Override
	public void resize(int width, int height) {
		camera.update();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		dispose();
		
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	
	}

}
