package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import ronan_hanley.inside_av.InsideAV;

public class Tier1RocketWeaponSystem extends RocketWeaponSystem {
	private static final Image SPRITE;
	private static final Sound SHOOT_SOUND;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/rocket/tier1.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
		
		Sound sound = null;
		try {
			sound = new Sound("res/sound/sfx/rocket_shoot.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		SHOOT_SOUND = sound;
	}
	
	public Tier1RocketWeaponSystem(int x, int y) {
		super(x, y, SPRITE);
	}
	
	public int getFireInterval() {
		return 3 * InsideAV.FPS;
	}

	@Override
	protected Sound getShootSound() {
		return SHOOT_SOUND;
	}
	
	@Override
	public double getUpgradeCost() {
		return 600;
	}

	@Override
	public WeaponSystem getUpgradedWeapon() {
		return new Tier2RocketWeaponSystem(getTileX(), getTileY());
	}
	
}
