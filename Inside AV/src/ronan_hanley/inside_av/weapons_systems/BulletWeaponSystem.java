package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.LevelRoute;

public abstract class BulletWeaponSystem extends WeaponSystem {
	public static final double COST = 100;
	
	public BulletWeaponSystem(int x, int y, Image sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public double getCost() {
		return COST;
	}
	
	@Override
	public void pointTowardsTarget() {
		if (target == null) return;
		
		// -- predict where the target will be --
		/* This code mostly copy pasted from:
		 * http://danikgames.com/blog/how-to-intersect-a-moving-target-in-2d/
		 * 
		 * With some tweaks. Proved too difficult to do by myself.
		 */
		
		// Given: ux, uy, vmag (projectile speed), Ax, Ay, Bx, By
		double ux = target.getSpeed() * LevelRoute.OFFSETS[target.getDirection()][0];
		double uy = target.getSpeed() * LevelRoute.OFFSETS[target.getDirection()][1];
		double vMag = getBulletSpeed();
		double Ax = getXExact();
		double Ay = getYExact();
		double Bx = target.getXExact();
		double By = target.getYExact();
		
		// Find the vector AB
		double ABx = Bx - Ax;
		double ABy = By - Ay;

		// Normalize it
		double ABmag = Math.sqrt(ABx * ABx + ABy * ABy);
		ABx /= ABmag;
		ABy /= ABmag;

		// Project u onto AB
		double uDotAB = ABx * ux + ABy * uy;
		double ujx = uDotAB * ABx;
		double ujy = uDotAB * ABy;

		// Subtract uj from u to get ui
		double uix = ux - ujx;
		double uiy = uy - ujy;

		// Set vi to ui (for clarity)
		double vix = uix;
		double viy = uiy;

		// Calculate the magnitude of vj
		double viMag = Math.sqrt(vix * vix + viy * viy);
		double vjMag = Math.sqrt(vMag * vMag - viMag * viMag);

		// Get vj by multiplying it's magnitude with the unit vector AB
		double vjx = ABx * vjMag;
		double vjy = ABy * vjMag;

		// Add vj and vi to get v
		double vx = vjx + vix;
		double vy = vjy + viy;
		
		// finally convert component parts into an angle
		double turretAngle = Math.atan2(vy, vx);
		
		setAngle(turretAngle);
	}
	
	@Override
	public void fire() {
		// spawn a bullet
		addProjectile(new Bullet(getX() + (InsideAV.TILE_SIZE / 2) - 2, getY() + (InsideAV.TILE_SIZE / 2) - 2, getAngle(), getBulletSpeed()));
	}
	
	public abstract double getBulletSpeed();
	
}
