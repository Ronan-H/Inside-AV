package ronan_hanley.inside_av;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ronan_hanley.inside_av.enemy.Enemy;
import ronan_hanley.inside_av.enemy.Rootkit;
import ronan_hanley.inside_av.enemy.TrackingCookie;
import ronan_hanley.inside_av.enemy.Virus;

/**
 * Contains information of the enemies in a wave, and
 * the timing of how the spawn.
 * @author Ronan
 */
public class Wave {
	private int[] enemyIDs;
	private double[] spawnTimes;
	private LevelRoute levelRoute;
	private int ticksSinceSpawn;
	private int enemiesSpawned;
	private int spawnX, spawnY;
	
	public Wave(String infoPath, LevelRoute levelRoute) {
		this.levelRoute = levelRoute;
		
		// Store the info in an ArrayList first, as we don't know how much info there is
		ArrayList<Double> spawnTimesList = new ArrayList<Double>();
		ArrayList<Integer> enemyIDList = new ArrayList<Integer>();
		
		try {
			BufferedReader waveInfoIn = new BufferedReader(new FileReader(infoPath));
			
			while (waveInfoIn.ready()) {
				StringTokenizer st = new StringTokenizer(waveInfoIn.readLine());
				
				spawnTimesList.add(Double.parseDouble(st.nextToken()));
				enemyIDList.add(Integer.parseInt(st.nextToken()));
			}
			
			waveInfoIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// convert list to array
		enemyIDs = new int[enemyIDList.size()];
		spawnTimes = new double[spawnTimesList.size()];
		
		for (int i = 0; i < spawnTimesList.size(); ++i) {
			spawnTimes[i] = spawnTimesList.get(i);
		}
		
		for (int i = 0; i < enemyIDList.size(); ++i) {
			enemyIDs[i] = enemyIDList.get(i);
		}
		
		// convert seconds to game ticks
		for (int i = 0; i < spawnTimes.length; ++i) {
			spawnTimes[i] *= InsideAV.FPS;
		}
		
		ticksSinceSpawn = enemiesSpawned = 0;
		
		// Find spawn location
		BufferedImage routeImg = levelRoute.getImage();
		
		outerLoop:
		for (int y = 0; y < routeImg.getHeight(); ++y) {
			for (int x = 0; x < routeImg.getWidth(); ++x) {
				if (routeImg.getRGB(x, y) == LevelRoute.START_COLOR) {
					spawnX = (x * InsideAV.TILE_SIZE) - InsideAV.TILE_SIZE;
					spawnY = y * InsideAV.TILE_SIZE;
					
					break outerLoop;
				}
			}
		}
	}
	
	/**
	 * Add a new enemy if it's time
	 * @param enemies
	 * @boolean wave finished?
	 */
	public boolean updateWave(ArrayList<Enemy> enemies) {
		if (enemiesSpawned >= spawnTimes.length) {
			// all enemies already spawned
			return true;
		}
		
		if (ticksSinceSpawn >= spawnTimes[enemiesSpawned]) {
			Enemy enemyToSpawn = null;
			
			switch (enemyIDs[enemiesSpawned]) {
			case 0:
				enemyToSpawn = new TrackingCookie(spawnX, spawnY, levelRoute);
				break;
			case 1:
				enemyToSpawn = new Virus(spawnX, spawnY, levelRoute);
				break;
			case 2:
				enemyToSpawn = new Rootkit(spawnX, spawnY, levelRoute);
				break;
			}
			
			enemies.add(enemyToSpawn);
			ticksSinceSpawn = 0;
			++enemiesSpawned;
		}
		
		++ticksSinceSpawn;
		
		return (enemiesSpawned >= enemyIDs.length);
	}
	
}
