package ronan_hanley.inside_av;

/**
 * The top superclass of probably everything in the game that appears
 * on the screen; it needs an x and y location.
 * 
 * Enemies, weapons, projectiles, all extend this class.
 * @author Ronan
 */
public abstract class Entity {
	private double x;
	private double y;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
