package ronan_hanley.inside_av.weapons_systems;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import ronan_hanley.inside_av.enemy.Enemy;

public final class Tier3LaserWeaponSystem extends LaserWeaponSystem {
	private static final Image SPRITE;
	private static final Sound SHOOT_SOUND;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/laser/tier3.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
		
		Sound sound = null;
		// TODO load shoot sound
		/*try {
			// sound = new Sound("res/sound/sfx/laser_shoot.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}*/
		SHOOT_SOUND = sound;
	}
	
	public Tier3LaserWeaponSystem(int x, int y, ArrayList<Enemy> enemies) {
		super(x, y, SPRITE, enemies);
	}
	
	@Override
	public int getFireInterval() {
		return 0;
	}

	@Override
	protected Sound getShootSound() {
		return SHOOT_SOUND;
	}

	@Override
	public int getMaxDamage() {
		return 8;
	}
	
	@Override
	public double getUpgradeCost() {
		// can not upgrade
		return -1;
	}

	@Override
	public WeaponSystem getUpgradedWeapon() {
		return null;
	}
	
	
}
