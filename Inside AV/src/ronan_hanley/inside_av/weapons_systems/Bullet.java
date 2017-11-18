package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet extends Projectile {
	public static final Image SPRITE;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/bullet/bullet.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
	}
	
	public Bullet(int x, int y, double angle, double speed) {
		super(x, y, angle, speed, SPRITE);
	}

	@Override
	public int getDamage() {
		return 10;
	}

	@Override
	public int getHalfWidth() {
		return 2;
	}

	@Override
	public int getHalfHeight() {
		return 2;
	}

}
