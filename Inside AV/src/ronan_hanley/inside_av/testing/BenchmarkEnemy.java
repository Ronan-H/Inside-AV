package ronan_hanley.inside_av.testing;

import ronan_hanley.inside_av.enemy.Enemy;

public final class BenchmarkEnemy extends Enemy {

	public BenchmarkEnemy() {
		super(0, 0, 0, null, null);
	}

	@Override
	public double getSystemDamage() {
		return 0;
	}

	@Override
	public double getSpeed() {
		return 0;
	}

	@Override
	public double getKillReward() {
		return 0;
	}

}
