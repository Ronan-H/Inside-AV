package ronan_hanley.inside_av.testing;

import java.util.ArrayList;
import java.util.Random;

import ronan_hanley.inside_av.Entity;
import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.enemy.Enemy;
import ronan_hanley.inside_av.weapons_systems.Projectile;

/**
 * This class is used to measure the efficiency of the collision
 * detection between the projectiles and the enemies, as if it is
 * too slow, the overhead can be huge and the game can slow down.
 * 
 * (When you're comparing every PROJECTILE on screen to every
 * ENEMY on screen, every TICK (1/60 of a second), maybe this
 * slow down is not so surprising!)
 * @author Ronan
 *
 */
public final class CollisionDetectionBenchmark {
	
	public static void main(String[] args) {
		final int NUM_ROUNDS = 10000;
		
		// create objects
		Random random = new Random();
		Projectile p = new BenchmarkProjectile();
		Enemy e = new BenchmarkEnemy();
		ArrayList<Entity> entities = new ArrayList<Entity>();
		entities.add(p);
		entities.add(e);
		
		// create an array of random positions
		int[][][] positions = new int[NUM_ROUNDS][entities.size()][2];
		
		for (int i = 0; i < NUM_ROUNDS; ++i) {
			for (int j = 0; j < entities.size(); ++j) {
				positions[i][j][0] = random.nextInt(InsideAV.SCREEN_WIDTH);
				positions[i][j][1] = random.nextInt(InsideAV.SCREEN_HEIGHT);
			}
		}
		
		// begin benchmark
		long startNS = System.nanoTime();
		
		for (int i = 0; i < NUM_ROUNDS; ++i) {
			// assign random positions
			for (int j = 0; j < entities.size(); ++j) {
				entities.get(j).setX(positions[i][j][0]);
				entities.get(j).setY(positions[i][j][1]);
			}
			
			p.touchingEnemy(e);
		}
		
		long endNS = System.nanoTime();
		long timeTaken = endNS - startNS;
		long avgTimeNS = timeTaken / NUM_ROUNDS;
		double avgMsTaken = (double)avgTimeNS / 1000000d;
		System.out.printf("Average time taken: %fms%n", avgMsTaken);
	}
	
}
