package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.RotationalEntity;
import ronan_hanley.inside_av.enemy.Enemy;

/**
 * Represents something that is fired from a weapon.
 * Probably travels across the screen until it hits an
 * enemy.
 * @author Ronan
 *
 */
public abstract class Projectile extends RotationalEntity {
	private double speed;
	private Image sprite;
	
	public Projectile(int x, int y, double angle, double speed, Image sprite) {
		super(x, y);
		setAngle(angle);
		setSpeed(speed);
		
		this.sprite = sprite;
	}
	
	public void update() {
		/* use some trigonometry to break an angle and a speed up
		 * into it's component parts
		 */
		changeX(Math.cos(getAngle()) * getSpeed());
		changeY(Math.sin(getAngle()) * getSpeed());
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX(), getY());
	}
	
	public abstract int getHalfWidth();
	public abstract int getHalfHeight();
	
	public boolean touchingEnemy(Enemy enemy) {
		/* Simplifying an enemy to be a circle. This is drastically faster
		 * than pixel perfect collision detection, as we simply check if the distance
		 * is less than the radius of the circle.
		 * 
		 * The circle's diameter is 1 game tile long.
		 */
		
		// pythagoras' theorem for distance
		int distance = (int) Math.sqrt(
			Math.pow((getXExact() + getHalfWidth()) - enemy.getCentreX(), 2)
		  + Math.pow((getYExact() + getHalfHeight()) - enemy.getCentreY(), 2));
		
		boolean colliding = (distance < InsideAV.HALF_TILE_SIZE + getHalfHeight());
		
		return colliding;
	}
	
	public abstract int getDamage();
	
}
