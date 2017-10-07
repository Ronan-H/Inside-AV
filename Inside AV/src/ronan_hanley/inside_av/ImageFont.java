package ronan_hanley.inside_av;

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
public class ImageFont {
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
		
		createFont();
	}
	
	/**
	 * Populates the chars array, allowing use of this font.
	 * 
	 * Should be called only once on creation of this object.
	 */
	private void createFont() {
		for (int i = 0; i < format.length(); ++i) {
			
		}
	}
	
}