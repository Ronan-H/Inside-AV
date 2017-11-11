package ronan_hanley.inside_av.weapons_systems;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ronan_hanley.inside_av.Entity;
import ronan_hanley.inside_av.InsideAV;

public abstract class WeaponSystem extends Entity {
	private Image sprite;
	private Image ammo;
	// angle, in radians
	private double angle;
	
	public WeaponSystem(int x, int y, String spritePath) {
		super(x * InsideAV.TILE_SIZE, y * InsideAV.TILE_SIZE);
		try {
			sprite = new Image(String.format("res/images/weapons/%s", spritePath), false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		sprite.setRotation((float) Math.toDegrees(angle));
		g.drawImage(sprite, getX(), getY());
	}
	
	/**
	 * 
	 * @return How much this weapon costs to buy
	 */
	public abstract double getCost();
	
}
