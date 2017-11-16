package ronan_hanley.inside_av.weapons_systems;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class WeaponSystemGrid {
	private WeaponSystem[][] grid;
	private ArrayList<WeaponSystem> weaponSystems;
	
	public WeaponSystemGrid(int width, int height) {
		grid = new WeaponSystem[height][width];
		weaponSystems = new ArrayList<WeaponSystem>();
	}
	
	public void addWeaponSystem(WeaponSystem weapon, int x, int y) {
		grid[y][x] = weapon;
		weaponSystems.add(weapon);
	}
	
	public boolean tileHasWeapon(int x, int y) {
		return (grid[y][x] != null);
	}
	
	public void renderAll(Graphics g) {
		for (WeaponSystem weapon : weaponSystems) {
			weapon.render(g);
		}
	}
	
}
