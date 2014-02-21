package com.platformer.platformer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
/**
 * This screen is the "welcome screen" , that shows us the message "welcome to soldat". This screen takes the Platformer platform object in constructor.
 * This screen will use the Platformer object to swap over to the game screen when the user clicks/taps the screen
 * @author jeppe
 *
 */
public class MainMenuScreen implements Screen {
	
	
	final Platformer game;
	static final boolean debug = true; 
	OrthographicCamera camera;
	
	public MainMenuScreen(Platformer platformer) {
		game = platformer;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to the game!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			
			if(debug)
			{
				game.setScreen(new GameScreen(game));
			}
			
			dispose();
		}
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
