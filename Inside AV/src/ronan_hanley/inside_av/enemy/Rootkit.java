package ronan_hanley.inside_av.enemy;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ronan_hanley.inside_av.LevelRoute;

public class Rootkit extends Enemy {
	private static Image SPRITE;
	
	static {
		// initialize the sprite
		try {
			SPRITE = new Image("res/images/enemies/rootkit.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Rootkit(int x, int y, LevelRoute route) {
		super(x, y, 1.0, 1000, SPRITE, route);
	}
	
	public double getSystemDamage() {
		return 300;
	}
	
}