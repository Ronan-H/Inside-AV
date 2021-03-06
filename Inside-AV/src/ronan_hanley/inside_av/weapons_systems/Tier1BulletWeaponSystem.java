package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public final class Tier1BulletWeaponSystem extends BulletWeaponSystem {
	private static final Image SPRITE;
	private static final Sound SHOOT_SOUND;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/bullet/tier1.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
		
		Sound sound = null;
		try {
			sound = new Sound("res/sound/sfx/bullet_shoot.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		SHOOT_SOUND = sound;
	}
	
	public Tier1BulletWeaponSystem(int x, int y) {
		super(x, y, SPRITE);
	}
	
	public int getFireInterval() {
		return 20;
	}

	@Override
	public double getBulletSpeed() {
		return 3.5;
	}

	@Override
	protected Sound getShootSound() {
		return SHOOT_SOUND;
	}
	
	@Override
	public double getUpgradeCost() {
		return 200;
	}

	@Override
	public WeaponSystem getUpgradedWeapon() {
		return new Tier2BulletWeaponSystem(getTileX(), getTileY());
	}
	
}
