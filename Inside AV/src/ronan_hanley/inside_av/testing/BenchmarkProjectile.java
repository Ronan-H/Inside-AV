package ronan_hanley.inside_av.testing;

import ronan_hanley.inside_av.weapons_systems.Projectile;

public final class BenchmarkProjectile extends Projectile {

	public BenchmarkProjectile() {
		super(0, 0, 0, 0, null);
	}

	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public int getHalfWidth() {
		return 2;
	}

	@Override
	public int getHalfHeight() {
		return 2;
	}

}
