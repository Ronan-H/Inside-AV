package ronan_hanley.inside_av.game_states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.menu_button.ButtonSet;

public class MenuState extends BasicGameState {
	private Image background;
	private ButtonSet menuButtons;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		background = new Image("res/menu_background.png");
		String[] buttonLabels = {"Play", "Level Select", "Exit"};
		menuButtons = new ButtonSet(buttonLabels, 27, 75, 300, 40, new Color(193, 25, 0), new Color(150, 20, 0), 15);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// check if user is hovering over a button and change it's color
		
	}
	
	@Override
	public void	mousePressed(int button, int x, int y) {
		// check if the user clicked a button
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(InsideAV.screenScale, InsideAV.screenScale);
		
		g.drawImage(background, 0, 0, null);
		
		final Color MENU_TEXT_COLOR = Color.white;
		
		final int TITLE_SCALE = 7;
		InsideAV.font.drawString("Inside AV",
			InsideAV.SCREEN_WIDTH /2, TITLE_SCALE,
			MENU_TEXT_COLOR, TITLE_SCALE, true, g);
		
		final int BOTTOM_TEXT_SCALE = 2;
		InsideAV.font.drawString("A game by Ronan Hanley", InsideAV.SCREEN_WIDTH /2, InsideAV.SCREEN_HEIGHT - (InsideAV.font.getCharHeight() * BOTTOM_TEXT_SCALE), MENU_TEXT_COLOR, BOTTOM_TEXT_SCALE, true, g);
		
		menuButtons.render(g);
	}

	@Override
	public int getID() {
		return 0;
	}

}
