package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public final class Tier2BulletWeaponSystem extends BulletWeaponSystem {
	private static final Image SPRITE;
	private static final Sound SHOOT_SOUND;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/bullet/tier2.png", false, Image.FILTER_NEAREST);
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
	
	public Tier2BulletWeaponSystem(int x, int y) {
		super(x, y, SPRITE);
	}
	
	@Override
	public void fire() {
		// the distance from the centre of this weapon to it's barrels
		int barrelDistance = 6;
		
		int offsetX, offsetY;
		
		offsetX = (int) (Math.cos(getAngle() + (Math.PI /2)) * barrelDistance);
		offsetY = (int) (Math.sin(getAngle() + (Math.PI /2)) * barrelDistance);
		
		addProjectile(new Bullet(getCentreX() - 2 + offsetX,
			getCentreY() - 2 + offsetY,
			getAngle(),
			getBulletSpeed()));
		
		offsetX = (int) (Math.cos(getAngle() - (Math.PI /2)) * barrelDistance);
		offsetY = (int) (Math.sin(getAngle() - (Math.PI /2)) * barrelDistance);
		
		addProjectile(new Bullet(getCentreX() - 2 + offsetX,
			getCentreY() - 2 + offsetY,
			getAngle(),
			getBulletSpeed()));
		
		playShootSound();
	}
	
	public int getFireInterval() {
		return 15;
	}

	@Override
	public double getBulletSpeed() {
		return 4.0;
	}

	@Override
	protected Sound getShootSound() {
		return SHOOT_SOUND;
	}
	
	@Override
	public double getUpgradeCost() {
		return 500;
	}

	@Override
	public WeaponSystem getUpgradedWeapon() {
		return new Tier3BulletWeaponSystem(getTileX(), getTileY());
	}
	
}
