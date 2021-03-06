package ronan_hanley.inside_av;

import ronan_hanley.inside_av.enemy.Enemy;

/**
 * A direction and a target location. Enemies use
 * a list of these to correctly make their way around
 * the map.
 * @author Ronan
 */
public final class LevelRouteInstruction {
	// clockwise from up (0=up, 1=right, 2=down, 3=left)
	private int direction;
	private int targetTileX;
	private int targetTileY;
	
	public LevelRouteInstruction(int direction, int targetTileX, int targetTileY) {
		this.direction = direction;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getTargetTileX() {
		return targetTileX;
	}
	
	public int getTargetTileY() {
		return targetTileY;
	}
	
	public boolean hasReachedTarget(Enemy enemy) {
		int tileX = enemy.getTileX();
		int tileY = enemy.getTileY();
		
		switch (direction) {
		case 0:
			return (tileY < targetTileY);
		case 1:
			return (tileX > targetTileX - 1);
		case 2:
			return (tileY > targetTileY - 1);
		case 3:
			return (tileX < targetTileX);
		default:
			// invalid direction
			System.err.println("Invalid direction in levelRouteInstructions hasReachedTarget()!");
			return false;
		}
	}
	
	@Override
	/**
	 * Used in the testing class.
	 */
	public String toString() {
		return String.format("Dir: %d targetX: %d targetY: %d", direction, targetTileX, targetTileY);
	}
	
}


