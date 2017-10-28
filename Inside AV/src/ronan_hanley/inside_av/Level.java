package ronan_hanley.inside_av;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public final class Level {
	private Image solidImage;
	private LevelRoute route;
	
	public Level(int levelNumber) {
		route = new LevelRoute(levelNumber);
		
		String solidImgPath = String.format("res/images/level_textures/level_%d_solid_tile.png", levelNumber);
		try {
			solidImage = new Image(solidImgPath);
		} catch (SlickException e) {
			/* This exception should never happen as long as the files
			 * are in the right place, so just print the stack trace.
			 */
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		// draw the solid tiles
		BufferedImage levelImage = route.getImage();
		for (int y = 0; y < levelImage.getHeight(); ++y) {
			for (int x = 0; x < levelImage.getWidth(); x++) {
				if (levelImage.getRGB(x, y) == LevelRoute.SOLID_COLOR) {
					g.drawImage(solidImage, x * InsideAV.TILE_SIZE, y * InsideAV.TILE_SIZE);
				}
			}
		}
	}
	
}
