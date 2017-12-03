package ronan_hanley.inside_av;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import ronan_hanley.inside_av.enemy.Enemy;

public final class Level {
	private int levelNumber;
	private Image solidImage;
	private LevelRoute route;
	private Wave[] waves;
	private int currentWave;
	// true if enemies are being spawned
	private boolean waveActive = false;
	// game ticks since this wave started
	private int waveTimer = 0;
	// true if all waves have been completed
	private boolean levelFinished;
	private Music levelMusic;
	
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		route = new LevelRoute(levelNumber);
		
		String solidImgPath = String.format("res/images/level_textures/level_%d_solid_tile.png", levelNumber);
		try {
			solidImage = new Image(solidImgPath, false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			/* This exception should never happen as long as the files
			 * are in the right place, so just print the stack trace.
			 */
			e.printStackTrace();
		}
		
		// Load in the wave info
		File[] waveFiles = new File("res/wave_info/level_" + levelNumber).listFiles();
		int numWaves = waveFiles.length;
		waves = new Wave[numWaves];
		
		for (int i = 0; i < numWaves; ++i) {
			waves[i] = new Wave(waveFiles[i].getPath(), route);
		}
		
		currentWave = 0;
		
		try {
			levelMusic = new Music(String.format("res/sound/music/level_%d_music.ogg", levelNumber));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		/* Start the music, unless this is level one, where
		 * the music should start once the player reaches the 
		 * tutorial, instead of when the level is loaded.
		 */
		if (levelNumber != 1) loopMusic();
	}
	
	public void update(ArrayList<Enemy> enemies) {
		if (waveActive) {
			boolean lastEnemy = waves[currentWave].updateWave(enemies);
			
			if (lastEnemy && enemies.size() == 0) {
				setWaveActive(false);
				++currentWave;
				
				if (currentWave >= waves.length) {
					levelFinished = true;
					levelMusic.stop();
				}
			}
			
			++waveTimer;
		}
	}
	
	public void render(Graphics g) {
		// draw the solid tiles
		BufferedImage levelImage = route.getImage();
		for (int y = 0; y < levelImage.getHeight(); ++y) {
			for (int x = 0; x < levelImage.getWidth(); x++) {
				if (levelImage.getRGB(x, y) == LevelRoute.SOLID_COLOR) {
					g.drawImage(solidImage, x * InsideAV.TILE_SIZE, y * InsideAV.TILE_SIZE);
				}
			}
		}
	}
	
	public boolean isWaveActive() {
		return waveActive;
	}
	
	public void setWaveActive(boolean state) {
		waveActive = state;
	}
	
	public boolean solidAt(int x, int y) {
		return route.solidAt(x, y);
	}
	
	public int getWaveTimer() {
		return waveTimer;
	}
	
	public boolean isLevelFinished() {
		return levelFinished;
	}
	
	public int getLevelNumber() {
		return levelNumber;
	}
	
	public void loopMusic() {
		levelMusic.loop(1f, 0.03f);
	}
	
	public void stopMusic() {
		levelMusic.stop();
	}
	
}
