package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public final class Tier1LaserWeaponSystem extends LaserWeaponSystem {
	public static final Image SPRITE;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/laser/tier1.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
	}
	
	public Tier1LaserWeaponSystem(int x, int y) {
		super(x, y, SPRITE);
	}
	
	@Override
	public int getFireInterval() {
		return 0;
	}
	
}
