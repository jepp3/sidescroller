package com.platformer.platformer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.platformer.screens.MainMenuScreen;

 

/**
 *  This is the so called bootstraper. Its here every thing begins. The platformer class will call the welcome screen, and send it self with it.
 *  
 *   The Platformer acts lika a router , it routes the game trough the different screens. 
 * @author jeppe
 *
 */
public class Platformer extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	
	
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new MainMenuScreen(this));
	}
	
	
	public void render() {
		super.render(); //importante!
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
