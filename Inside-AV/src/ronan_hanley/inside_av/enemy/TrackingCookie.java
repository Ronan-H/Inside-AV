package ronan_hanley.inside_av.enemy;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ronan_hanley.inside_av.LevelRoute;

public final class TrackingCookie extends Enemy {
	private static Image SPRITE;
	
	static {
		// initialize the sprite
		try {
			SPRITE = new Image("res/images/enemies/tracking_cookie.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public TrackingCookie(int x, int y, LevelRoute route) {
		super(x, y, 20, SPRITE, route);
	}
	
	public double getSystemDamage() {
		return 50;
	}
	
	@Override
	public double getSpeed() {
		return 2.5;
	}

	@Override
	public double getKillReward() {
		return 20;
	}
	
}
