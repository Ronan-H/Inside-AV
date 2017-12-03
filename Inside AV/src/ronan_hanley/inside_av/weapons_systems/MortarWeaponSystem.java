package ronan_hanley.inside_av.weapons_systems;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.enemy.Enemy;

public abstract class MortarWeaponSystem extends WeaponSystem {
	public static final double COST = 300;
	protected ArrayList<Enemy> enemies;
	
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
		
		target = WeaponSystem.findCentralEnemy(enemies);
	}
	
	@Override
	public void fire() {
		if (target != null) {
			// fire a mortar towards the target
			Projectile mortar = new Mortar(getX() + InsideAV.HALF_TILE_SIZE, getY() + InsideAV.HALF_TILE_SIZE, getAngle(), 2.5, target.getX(), target.getY());
			addProjectile(mortar);
		}
		
		playShootSound();
	}
	
}
