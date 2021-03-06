package ronan_hanley.inside_av.weapons_systems;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.QuadraticDamageSource;
import ronan_hanley.inside_av.enemy.Enemy;

public abstract class LaserWeaponSystem extends WeaponSystem {
	public static final double COST = 200;
	protected ArrayList<Enemy> enemies;
	// the distance an enemy is outside where they take no damage from the laser
	private static final int DAMAGE_RADIUS = InsideAV.TILE_SIZE * 6;
	
	public LaserWeaponSystem(int x, int y, Image sprite, ArrayList<Enemy> enemies) {
		super(x, y, sprite);
	}
	
	@Override
	public void update(ArrayList<Enemy> enemies) {
		super.update(enemies);
		
		target = WeaponSystem.findCentralEnemy(enemies);
		
		/* damage enemies touching the laser, based on their distance
		 * to it.
		 * 
		 * given a point (m, n) and a line Ax + By + C = 0,
		 * distance = |Am + Bn + C| / sqrt(A^2 + B^2)
		 */
		
		QuadraticDamageSource damageSource =
			new QuadraticDamageSource(getCentreX(), getCentreY(), getMaxDamage(), DAMAGE_RADIUS);
		
		double A = Math.sin(getAngle()) / Math.cos(getAngle());
		double B = -1;
		double C = getCentreY() - A * getCentreX();
		
		for (Enemy enemy : enemies) {
			/* This simplifies a line segment to a line. To stop enemies
			 * being damaged on the other "side" of the line (behind the weapon),
			 * first check to ensure the enemy is 
			 */
			double angleToEnemy = Math.atan2(enemy.getY() - getY(), enemy.getX() - getX());
			
			double minAngle = angleToEnemy - Math.PI /2;
			double maxAngle = angleToEnemy + Math.PI /2;
			
			if (!(getAngle() > minAngle && getAngle() < maxAngle)) {
				continue;
			}
			
			double m = enemy.getCentreX();
			double n = enemy.getCentreY();
			
			// distance to the line
			double distance = Math.abs(A*m + B*n + C) / Math.sqrt(Math.pow(A, 2) + Math.pow(B, 2));
			
			if (distance <= InsideAV.HALF_TILE_SIZE) {
				/* enemy touching laser; damage it based off
				 * how far it is from the weapon
				 */
				
				damageSource.damageEnemy(enemy);
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if (target != null) {
			// draw the laser
			
			g.setColor(Color.red);
			
			// long enough to always reach the end of the screen
			int laserLen = 2 * InsideAV.SCREEN_HEIGHT;
			int laserLenX = (int) (Math.cos(getAngle()) * laserLen);
			int laserLenY = (int) (Math.sin(getAngle()) * laserLen);
			
			g.drawLine(getCentreX(), getCentreY(), getCentreX() + laserLenX, getCentreY() + laserLenY);
		}
		
	}
	
	@Override
	public double getCost() {
		return COST;
	}
	
	/**
	 * This weapon shoots a laser continuously, so this method is
	 * not used.
	 */
	@Override
	public void fire() {}
	
	@Override
	public void playShootSound() {}
	
	/**
	 * How much damage is done to an enemy on top of the weapon
	 * 
	 * (damage falls off with distance)
	 * @return
	 */
	public abstract int getMaxDamage();
	
}
