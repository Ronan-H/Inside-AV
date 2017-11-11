package ronan_hanley.inside_av;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class DrawableEntity extends Entity {
	private Image sprite;
	
	public DrawableEntity(int x, int y, Image sprite) {
		super(x, y);
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX(), getY());
	}
	
}
