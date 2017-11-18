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
	private int[][] info;
	private LevelRoute levelRoute;
	private int ticksSinceSpawn;
	private int enemiesSpawned;
	private int spawnX, spawnY;
	
	public Wave(String infoPath, LevelRoute levelRoute) {
		this.levelRoute = levelRoute;
		
		// Store the info in an ArrayList first, as we don't know how much info there is
		ArrayList<ArrayList<Integer>> infoList = new ArrayList<ArrayList<Integer>>();
		
		try {
			BufferedReader waveInfoIn = new BufferedReader(new FileReader(infoPath));
			
			while (waveInfoIn.ready()) {
				ArrayList<Integer> lineList = new ArrayList<Integer>();
				
				StringTokenizer st = new StringTokenizer(waveInfoIn.readLine());
				while (st.hasMoreTokens()) {
					lineList.add(Integer.parseInt(st.nextToken()));
				}
				
				infoList.add(lineList);
			}
			
			waveInfoIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// convert list to array
		info = new int[infoList.size()][2];
		
		for (int i = 0; i < infoList.size(); ++i) {
			for (int j = 0; j < infoList.get(i).size(); ++j) {
				info[i][j] = infoList.get(i).get(j);
			}
		}
		
		// convert seconds to game ticks
		for (int i = 0; i < info.length; ++i) {
			info[i][0] *= InsideAV.FPS;
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
		if (ticksSinceSpawn >= info[enemiesSpawned][0]) {
			Enemy enemyToSpawn = null;
			
			switch (info[enemiesSpawned][1]) {
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
		
		return (enemiesSpawned >= info.length);
	}
	
}
