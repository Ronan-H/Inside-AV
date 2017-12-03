package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;

import ronan_hanley.inside_av.InsideAV;

public abstract class RocketWeaponSystem extends WeaponSystem {
	public static final double COST = 400;
	
	public RocketWeaponSystem(int x, int y, Image sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public double getCost() {
		return COST;
	}
	
	@Override
	public void fire() {
		// fires a rocket
		addProjectile(new Rocket(getX() + InsideAV.HALF_TILE_SIZE, getY() + InsideAV.HALF_TILE_SIZE, 0.2, this));
		
		playShootSound();
	}
	
}
