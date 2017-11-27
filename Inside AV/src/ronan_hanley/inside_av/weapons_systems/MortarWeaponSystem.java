package ronan_hanley.inside_av.weapons_systems;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import ronan_hanley.inside_av.enemy.Enemy;

public abstract class MortarWeaponSystem extends WeaponSystem {
	public static final double COST = 300;
	private ArrayList<Enemy> enemies;
	
	public MortarWeaponSystem(int x, int y, Image sprite, ArrayList<Enemy> enemies) {
		super(x, y, sprite);
		this.enemies = enemies;
	}
	
	@Override
	public double getCost() {
		return COST;
	}
	@Override
	public void update(ArrayList<Enemy> enemies) {
		super.update(enemies);
		
		if (enemies.size() == 0) {
			target = null;
		} else {
			/* finds the average position of the enemies and targets the 
			 * closest enemy to that position
			 * 
			 * (shooting at the average position could potentially not work,
			 * e.g. shooting between two far apart enemies)
			 */
			double totalX = 0;
			double totalY = 0;
			
			double avgX, avgY;
			
			for (Enemy enemy : enemies) {
				totalX += enemy.getXExact();
				totalY += enemy.getYExact();
			}
			
			avgX = totalX / enemies.size();
			avgY = totalY / enemies.size();
			
			// find the closest enemy to that
			Enemy closestEnemy = null;
			double shortestDistance = Double.MAX_VALUE;
			double distance;
			for (Enemy enemy : enemies) {
				distance = Math.sqrt(
					Math.pow(enemy.getXExact() - avgX, 2) + Math.pow(enemy.getYExact() - avgY, 2));
				if (distance < shortestDistance) {
					closestEnemy = enemy;
					shortestDistance = distance;
				}
			}
			
			target = closestEnemy;
		}
	}
	
	@Override
	public void fire() {
		if (target != null) {
			// fire a mortar towards the target
			Projectile mortar = new Mortar(getX(), getY(), getAngle(), 1.5, target.getX(), target.getY());
			addProjectile(mortar);
		}
	}
	
}
