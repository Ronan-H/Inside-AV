package ronan_hanley.inside_av.game_states;

/**
 * Has some constants to control the flow of the game.
 * 
 * Maybe an enum would be better here but I couldn't figure out how to
 * do that in a way that's easier than just doing global constants
 * like this.
 * @author Ronan
 */
public final class Substate {
	public static final int TUTORIAL = 0;
	public static final int PLAYING = 1;
	public static final int GAME_OVER = 2;
	public static final int GAME_WON = 3;
}
