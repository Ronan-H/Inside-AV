package ronan_hanley.inside_av.game_states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

/**
 * A "custom made" game state, to allow game states to enter new states
 * instead of having to use the StateBasedGame object directly.
 * @author Ronan
 *
 */
public abstract class InsideAVState extends BasicGameState {
	private StateBasedGame sbg;
	
	public InsideAVState(StateBasedGame sbg) {
		this.sbg = sbg;
	}
	
	@Override
	public abstract void init(GameContainer container, StateBasedGame game) throws SlickException;

	@Override
	public abstract void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;

	@Override
	public abstract void update(GameContainer container, StateBasedGame game, int delta) throws SlickException;

	@Override
	public abstract int getID();
	
	public void enterState(int id) {
		sbg.enterState(id);
	}
	
	protected void enterState(int id, Transition leave, Transition enter) {
		sbg.enterState(id, leave, enter);
	}

}
