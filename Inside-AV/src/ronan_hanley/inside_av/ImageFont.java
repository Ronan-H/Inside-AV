package ronan_hanley.inside_av;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * A class for using an image with characters
 * in it as a font.
 * 
 * Concept taken from Markus Persson's ludum dare
 * games (specifically Metagun http://ludumdare.com/compo/ludum-dare-18/?action=preview&uid=398)
 * 
 * All code and assets relating to this were created by me, though.
 * Nothing copy pasted.
 * 
 * @author Ronan
 */
public final class ImageFont {
	private Image charSheet;
	private String format;
	private int charWidth;
	private int charHeight;
	private Image[] chars;
	
	/**
	 * 
	 * @param charSheet The image containing all the characters for the font.
	 * @param format How the characters are laid out in the charSheet.
	 * @param charWidth Width of each character, in pixels.
	 * @param charHeight Height of each character, in pixels. 
	 */
	public ImageFont(Image charSheet, String format, int charWidth, int charHeight) {
		this.charSheet = charSheet;
		this.format = format;
		this.charWidth = charWidth;
		this.charHeight = charHeight;
		
		createFont();
	}
	
	public int getCharWidth() {
		return charWidth;
	}
	
	public int getCharHeight() {
		return charHeight;
	}
	
	/**
	 * Populates the chars array, allowing use of this font.
	 * 
	 * Should be called only once on creation of this object.
	 */
	private void createFont() {
		chars = new Image[format.length()];
		
		final int ROW_LENGTH = charSheet.getWidth() / charWidth;
		
		for (int i = 0; i < format.length(); ++i) {
			int xPos = i % ROW_LENGTH;
			int yPos = i / ROW_LENGTH;
			
			chars[i] = charSheet.getSubImage(xPos * charWidth, yPos * charHeight, charWidth, charHeight);
		}
	}
	
	/**
	 * Draws a string to a graphics object, using this
	 * font.
	 * 
	 * @param string The string to draw to the graphics oject.
	 * @param x Start of string (x). (top left of string)
	 * @param y Start of string (y). (top left of string)
	 * @param color The color to use when drawing this string.
	 * @param scale Scale of the drawn string.
	 * Eg. Scale of 5 means each pixel in the charSheet is 5x5 pixels when drawn.
	 * @param g The graphics object to draw to.
	 */
	public void drawString(String string, int x, int y, Color color, int scale, boolean centered, Graphics g) {
		int cursorX = x;
		int cursorY = y;
		
		int lineCount = 0;
		String[] lines = null;
		if (centered) {
			lines = string.split("\n");
			cursorX -= (lines[lineCount++].length() * scale * charWidth) / 2;
		}
		
		final int CHAR_SCREEN_WIDTH = charWidth * scale;
		final int CHAR_SCREEN_HEIGHT = charHeight * scale;
		
		string = string.toUpperCase();
		
		for (int i = 0; i < string.length(); ++i) {
			char c = string.charAt(i);
			int charIndex = format.indexOf(c);
			
			if (c == '\n') {
				cursorX = x;
				
				if (centered) {
					cursorX -= (lines[lineCount++].length() * scale * charWidth) / 2;
				}
				
				cursorY += CHAR_SCREEN_HEIGHT;
				continue;
			}
			
			// draw character
			g.drawImage(chars[charIndex],
				cursorX, cursorY, // screen start
				cursorX + CHAR_SCREEN_WIDTH, cursorY + CHAR_SCREEN_HEIGHT, // screen end
				0, 0, // image start
				charWidth, charHeight, // image end
				color);
			
			cursorX += CHAR_SCREEN_WIDTH;
		}
	}
	
}
