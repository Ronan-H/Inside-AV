package ronan_hanley.inside_av.game_states;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

/**
 * A custom game state, to allow game states to enter new states directly
 * instead of having to pass around the StateBasedGame object.
 * @author Ronan
 */
public abstract class InsideAVState extends BasicGameState {
	private StateBasedGame sbg;
	
	public InsideAVState(StateBasedGame sbg) {
		this.sbg = sbg;
	}
	
	protected void enterState(int id) {
		sbg.enterState(id);
	}
	
	protected void enterState(int id, Transition leave, Transition enter) {
		sbg.enterState(id, leave, enter);
	}
	
	@Override
	/**
	 * Mouse position should update even when the user is dragging the mouse.
	 */
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		mouseMoved(oldX, oldY, newX, newY);
	}
	
}
