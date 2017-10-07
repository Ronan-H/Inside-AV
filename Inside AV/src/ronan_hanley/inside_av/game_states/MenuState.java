package ronan_hanley.inside_av.game_states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ronan_hanley.inside_av.InsideAV;

public class MenuState extends BasicGameState {
	private Image background;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		background = new Image("res/menu_background.png");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(InsideAV.screenScale, InsideAV.screenScale);
		
		g.drawImage(background, 0, 0, null);
	}

	@Override
	public int getID() {
		return 0;
	}

}