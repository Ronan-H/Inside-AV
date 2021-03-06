package ronan_hanley.inside_av.game_states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ronan_hanley.inside_av.InsideAV;
import ronan_hanley.inside_av.menu_button.ButtonSet;

public final class MenuState extends InsideAVState {
	private Image background;
	private ButtonSet menuButtons;
	private static final int TITLE_SCALE = 7;
	private static Sound menuSelectSound;
	private boolean mouseHoveringButton = false;
	private boolean exitOnNextUpdate = false;
	private Music menuMusic;
	// a counter used to make the background's colour change
	private int updatesSinceStart = 0;
	
	public MenuState(StateBasedGame sbg) {
		super(sbg);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		menuMusic = new Music("res/sound/music/menu_music.ogg");
		menuMusic.loop(1f, 0.1f);
		
		background = new Image("res/images/menu_background.png");
		String[] buttonLabels = {"Play", "Exit"};
		menuButtons = new ButtonSet(buttonLabels,
									InsideAV.SCREEN_WIDTH / 2 - 75,
									(InsideAV.font.getCharHeight() + 4) * TITLE_SCALE,
									150, 40,
									Color.black, Color.green,
									15);
		menuSelectSound = new Sound("res/sound/sfx/menu_select.ogg");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (exitOnNextUpdate) {
			container.exit();
		}
		
		++updatesSinceStart;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.scale(InsideAV.screenScale, InsideAV.screenScale);
		
		// colour changing background
		final float CYCLE_LEN = 1500;
		float hue = (updatesSinceStart % CYCLE_LEN) / CYCLE_LEN;
		g.drawImage(background, 0, 0, new Color(java.awt.Color.HSBtoRGB(hue, 1, 1)));
		
		final Color MENU_TEXT_COLOR = Color.white;
		
		InsideAV.font.drawString("Inside AV",
								 InsideAV.SCREEN_WIDTH /2, TITLE_SCALE * 2,
								 MENU_TEXT_COLOR, TITLE_SCALE, true, g);
		
		final int BOTTOM_TEXT_SCALE = 2;
		InsideAV.font.drawString("A game by Ronan Hanley",
								 InsideAV.SCREEN_WIDTH /2,
								 InsideAV.SCREEN_HEIGHT - (InsideAV.font.getCharHeight() * BOTTOM_TEXT_SCALE),
								 MENU_TEXT_COLOR, BOTTOM_TEXT_SCALE,
								 true, g);
		
		menuButtons.render(g);
	}
	
	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		boolean wasHovering = mouseHoveringButton;
		// check if user is hovering over a button and change it's color
		// accounting for screen scale is annoying, maybe there's a better way
		mouseHoveringButton = menuButtons.updateButtons(newX / InsideAV.screenScale,
														newY / InsideAV.screenScale);
		
		if (mouseHoveringButton && !wasHovering) {
			/* Mouse is hovering over a button and it wasn't before;
			 * play the blip sound
			 */
			menuSelectSound.play(1f, 0.05f);
		}
	}
	
	@Override
	public void	mousePressed(int button, int x, int y) {
		int buttonClicked = menuButtons.mouseClicked(x, y);
		
		switch (buttonClicked) {
		case 0:
			// Play
			enterState(1, new FadeOutTransition(), new FadeInTransition());
			menuMusic.stop();
			break;
		case 1:
			// Exit
			// System.exit(0) won't work here because Slick needs to close resources cleanly
			exitOnNextUpdate = true;
			break;
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
