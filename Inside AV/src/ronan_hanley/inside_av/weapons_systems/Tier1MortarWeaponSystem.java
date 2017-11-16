package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ronan_hanley.inside_av.InsideAV;

public final class Tier1MortarWeaponSystem extends MortarWeaponSystem {
	public static final Image SPRITE;
	
	static {
		Image sprite = null;
		try {
			sprite = new Image("res/images/weapons/mortar/tier1.png", false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		SPRITE = sprite;
	}
	
	public Tier1MortarWeaponSystem(int x, int y) {
		super(x, y, SPRITE);
	}
	
	public int getFireInterval() {
		return 5 * InsideAV.FPS;
	}
	
}
