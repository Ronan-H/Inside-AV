package ronan_hanley.inside_av.enemy;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Virus extends Enemy {
	private static Image SPRITE;
	
	static {
		// initialize the sprite
		try {
			SPRITE = new Image("res/images/enemies/virus.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Virus(int x, int y) {
		super(x, y, SPRITE);
	}
	
}
