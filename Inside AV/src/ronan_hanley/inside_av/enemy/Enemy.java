package ronan_hanley.inside_av.enemy;

import org.newdawn.slick.Image;

import ronan_hanley.inside_av.Entity;

public abstract class Enemy extends Entity {
	private Image sprite;
	
	public Enemy(int x, int y, Image sprite) {
		super(x, y);
		this.sprite = sprite;
	}

}
