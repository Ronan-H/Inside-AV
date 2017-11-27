package ronan_hanley.inside_av.weapons_systems;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ronan_hanley.inside_av.enemy.Enemy;

public final class Rocket extends Projectile {
	public static final Image SPRITE;
	private Random random;
	// turning speed, in radians per tick
	private static final double TURN_SPEED = (Math.PI * 2) / 32;
	// the weapon this rocket belongs to (used for targeting)
	private WeaponSystem weapon;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/rocket/rocket.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
	}
	
	public Rocket(int x, int y, double speed, WeaponSystem weapon) {
		super(x, y, 0, speed, SPRITE);
		this.weapon = weapon;
				
		random = new Random();
		
		// make the angle random
		setAngle(random.nextDouble() * (Math.PI * 2));
	}
	
	@Override
	public void update() {
		super.update();

		/* rockets should gradually turn towards the enemy,
		 * while going forward 
		 */
		
		/* first find the angle the rocket should get closer to
		 * (will be the angle between the rocket and the enemy) 
		 */
		Enemy target = weapon.target;
		// inverse tan is useful here
		double targetAngle = Math.atan2(target.getYExact() - getYExact(), target.getXExact() - getXExact());
		
		/* now calculate how many radians we'd have to turn to
		 * meet the target angle 
		 */
		double difference = targetAngle - getAngle();
		// only change the angle if it's within a certain range
		/* this stops the rocket from constantly jiggling back and forth
		 * when it's close to the target angle
		 */
		if (Math.abs(difference) > TURN_SPEED * 2) {
			changeAngle(difference > 0 ? TURN_SPEED : -TURN_SPEED);
		}
		
		SPRITE.setRotation((float) Math.toDegrees(getAngle() + (Math.PI /2)));
		
		// make the rocket get faster and faster as it flies
		// (but have a maximum speed)
		setSpeed(Math.min(5, getSpeed() * 1.05));
	}
	
	@Override
	public int getDamage() {
		return 50;
	}

	@Override
	public int getHalfWidth() {
		return 3;
	}

	@Override
	public int getHalfHeight() {
		return 4;
	}
}
