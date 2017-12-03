package ronan_hanley.inside_av;

import ronan_hanley.inside_av.enemy.Enemy;

/**
 * Class used when damage should be applied using a quadratic equation;
 * when damage should fall off dramatically in a "parabolic" fashion,
 * instead of linearly.
 * 
 * An explosion is a good example of this kind of damage source.
 * @author Ronan
 */
public final class QuadraticDamageSource extends Entity{
	private double maxDamage;
	private double exponent;
	
	/**
	 * Creates a damage source using two values.
	 * @param maxDamage The damage taken when the enemy is directly
	 * on top of the source
	 * @param radius The radius of the damage area. Zero damage
	 * will be taken at a distance of radius from the source.
	 */
	public QuadraticDamageSource(int x, int y, int maxDamage, int radius) {
		super(x, y);
		this.maxDamage = maxDamage;
		/* Convert the given radius to something we can use in our
		 * formula (damage = -distance^exponent + maxDamage)
		 * 
		 * d = log(base = radius, m)
		 */
		exponent = logOfBase(radius, maxDamage);
	}
	
	/**
	 * Damages an enemy based off this damage source.
	 * @param enemy The enemy to damage.
	 */
	public void damageEnemy(Enemy enemy) {
		/* First find the distance from the centre of the source
		 * to the enemy 
		 */
		double distance = Math.sqrt(Math.pow(getXExact() - enemy.getXExact(), 2)
								  + Math.pow(getYExact() - enemy.getYExact(), 2));
		
		enemy.applyDamage(getDamageForDistance(distance));
	}
	
	public double getDamageForDistance(double distance) {
		// apply the damage formula (damage = -distance^exponent + maxDamage)
		double damage =  -(Math.pow(distance, exponent)) + maxDamage;
		
		return Math.max(damage, 0);
	}
	
	/**
	 * This method taken from:
	 * http://blog.dreasgrech.com/2010/02/finding-logarithm-of-any-base-in-java.html
	 * 
	 * Not sure why such a common maths function isn't included in the java.lang.Math library.
	 */
	public static double logOfBase(int base, int num) {
	    return Math.log(num) / Math.log(base);
	}
	
}
