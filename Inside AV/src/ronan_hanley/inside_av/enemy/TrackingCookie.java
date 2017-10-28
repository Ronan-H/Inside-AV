package ronan_hanley.inside_av.enemy;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TrackingCookie extends Enemy {
	private static Image SPRITE;
	
	static {
		// initialize the sprite
		try {
			SPRITE = new Image("res/images/enemies/tracking_cookie.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public TrackingCookie(int x, int y) {
		super(x, y, SPRITE);
	}

}
