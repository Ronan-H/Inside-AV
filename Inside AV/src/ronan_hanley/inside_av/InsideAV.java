package ronan_hanley.inside_av;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import ronan_hanley.inside_av.game_states.MenuState;
import ronan_hanley.inside_av.game_states.PlayingState;

/**
 * Project started on 4/10/17
 * @author Ronan Hanley
 *
 */
public class InsideAV extends StateBasedGame {
	// Some constants about the basics of the game
	private static final String GAME_NAME = "Inside AV";
	public static final int TILE_SIZE = 32;
	// Maybe doing these calculations in code is overkill
	// 480 x 480
	private static final int SCREEN_TILES_X = 15;
	private static final int SCREEN_TILES_Y = 15;
	public static final int SCREEN_WIDTH = SCREEN_TILES_X * TILE_SIZE;
	public static final int SCREEN_HEIGHT = SCREEN_TILES_Y * TILE_SIZE;
	public static int screenScale;
	
	public static ImageFont font;
	
	public InsideAV() {
		super(GAME_NAME);
	}
	
	private void init() {
		// initialize game font
		String format =
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?:;+-=*#%^&_|/\\~\"'`$@()[]{}<> ";
		try {
			font = new ImageFont(new Image("res/computer_font.png", false, Image.FILTER_NEAREST), format, 6, 9);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MenuState());
		addState(new PlayingState());
		
		init();
		
		// Enter menu state
		enterState(0);
	}
	
	// Main Method
	// Copied from http://slick.ninjacave.com/wiki/index.php?title=Game_States
	// But heavily modified
    public static void main(String[] args) {
        try {
        	final int FPS = 60;
        	final int LOGIC_INTERVAL = 1000 / FPS;
        	
            AppGameContainer app = new AppGameContainer(new InsideAV());
            
            // Calculate the window size
            // It will scale to be appropriate the screen size
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            /* Allow some space to the sides so the window
             * doesn't overlap over the taskbar etc.
             */
            screenSize.height -= 65;
            
            screenScale = screenSize.height / SCREEN_HEIGHT;
            final int ADJUSTED_WIDTH = SCREEN_HEIGHT * screenScale;
            final int ADJUSTED_HEIGHT = SCREEN_HEIGHT * screenScale;
            
            app.setDisplayMode(ADJUSTED_WIDTH, ADJUSTED_HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.setMaximumLogicUpdateInterval(LOGIC_INTERVAL);
            app.setMaximumLogicUpdateInterval(LOGIC_INTERVAL);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

}
