package com.platformer.platformer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Array;

/**
 * Loads all the sound that we need in the game, invoked by the Master Ass(et)Loader
 * @author jeppe
 *
 */
public class SoundLoader {
 
	private static Music backgroundMusic = null;
	public static void setSoundBank()
	{
		// to add music 
	}
	
	public static void loadSounds()
	{
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/background.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.3f);
		backgroundMusic.play();	
	}
}
