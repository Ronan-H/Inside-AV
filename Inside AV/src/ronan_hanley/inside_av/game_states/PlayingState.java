package ronan_hanley.inside_av.game_states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.Level;
import ronan_hanley.inside_av.enemy.Enemy;

public final class PlayingState extends InsideAVState {
	// the level the player is currently on
	private int level;
	private Level currentLevel;
	private ArrayList<Enemy> enemies;
	
	public PlayingState(StateBasedGame sbg) {
		super(sbg);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		level = 1;
		currentLevel = new Level(level);
		enemies = new ArrayList<Enemy>();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(InsideAV.screenScale, InsideAV.screenScale);
		currentLevel.render(g);
	}

	@Override
	public int getID() {
		return 1;
	}

}
