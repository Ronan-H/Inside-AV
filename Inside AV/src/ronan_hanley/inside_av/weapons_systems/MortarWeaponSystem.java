package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;

public abstract class MortarWeaponSystem extends WeaponSystem {
	public static final double COST = 300;
	
	public MortarWeaponSystem(int x, int y, Image sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public double getCost() {
		return COST;
	}
	
	@Override
	public void fire() {
		// TODO this method
	}
	
}
