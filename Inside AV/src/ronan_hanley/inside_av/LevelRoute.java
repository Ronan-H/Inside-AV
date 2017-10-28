package ronan_hanley.inside_av;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Represents the route the enemies will take
 * in the level
 * @author Ronan
 */
public final class LevelRoute {
	
	/**
	 * A direction and a target location. Enemies use
	 * a list of these to correctly make their way around
	 * the map.
	 * @author Ronan
	 */
	class LevelRouteInstruction {
		// clockwise from up (0=up, 1=right, 2=down, 3=left)
		private int direction;
		private int targetTileX;
		private int targetTileY;
		
		public LevelRouteInstruction(int direction, int targetTileX, int targetTileY) {
			this.direction = direction;
			this.targetTileX = targetTileX;
			this.targetTileY = targetTileY;
		}
		
		public int getDirection() {
			return direction;
		}
		
		public int getTargetTileX() {
			return targetTileX;
		}
		
		public int getTargetTileY() {
			return targetTileY;
		}
		
		@Override
		public String toString() {
			return String.format("Dir: %d targetX: %d targetY: %d", direction, targetTileX, targetTileY);
		}
		
	}
	
	public static final int START_COLOR = Color.GREEN.getRGB();
	public static final int END_COLOR = Color.RED.getRGB();
	public static final int ROUTE_COLOR = Color.BLACK.getRGB();
	public static final int SOLID_COLOR = Color.WHITE.getRGB();
	private BufferedImage image;
	private LevelRouteInstruction[] routeInstructions;
	
	public LevelRoute(BufferedImage image) {
		this.image = image;
		createRouteInstructions();
	}
	
	public LevelRoute(int levelNumber) {
		String imgPath = String.format("res/images/level_routes/level_%d_route.png", levelNumber);
		try {
			image = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			/* This exception should never happen as long as the files
			 * are in the right place, so just print the stack trace.
			 */
			e.printStackTrace();
		}
		
		createRouteInstructions();
	}
	
	private void createRouteInstructions() {
		ArrayList<LevelRouteInstruction> instructionsList = new ArrayList<LevelRouteInstruction>();
		
		// Locate the start and end
		Point startPoint = null;
		Point endPoint = null;
		boolean foundStart = false;
		boolean foundEnd = false;
		
		outerLoop:
		for (int y = 0; y < image.getHeight(); ++y) {
			for (int x = 0; x < image.getWidth(); ++x) {
				if (!foundStart && image.getRGB(x, y) == START_COLOR) {
					startPoint = new Point(x, y);
					foundStart = true;
				}
				
				if (!foundEnd && image.getRGB(x, y) == END_COLOR) {
					endPoint = new Point(x, y);
					foundEnd = true;
				}
				
				if (foundStart && foundEnd) {
					break outerLoop;
				}
			}
		}
		
		instructionsList.add(new LevelRouteInstruction(1, startPoint.x, startPoint.y));
		
		// Create route
		int x = startPoint.x;
		int y = startPoint.y;
		
		int lastX = -1000;
		int lastY = -1000;
		
		int[][] offsets = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
		int counter = 0;
		outerLoop:
		while ((x == endPoint.x && y == endPoint.y) == false) {
			System.out.printf("x: %d y: %d%n", x, y); 
			
			// Search for adjacent ROUTE_COLOR pixel
			for (int i = 0; i < offsets.length; ++i) {
				int newX = x + offsets[i][0];
				int newY = y + offsets[i][1];
				
				// DEBUG:
				//System.out.printf("current: (%d, %d) - last: (%d, %d) - new: (%d, %d)%n", x, y, lastX, lastY, newX, newY);
				
				// Check if this pixel is the one we came from
				if (newX == lastX && newY == lastY) {
					continue;
				}
				
				try {
					int pixelColor = image.getRGB(newX, newY);
					if (pixelColor == ROUTE_COLOR || pixelColor == END_COLOR) {
						instructionsList.add(new LevelRouteInstruction(i, newX, newY));
						
						lastX = x;
						lastY = y;
						
						x = newX;
						y = newY;
						continue outerLoop;
					}
				}catch (IndexOutOfBoundsException e) {}
			}
		}
		
		routeInstructions = optimizeInstructions(instructionsList);
	}
	
	/**
	 * Simplifies the set of instructions.
	 * 
	 * Optimizes like so (example):
	 * Go right 3 times --> Go right once, for the same distance as going 3 times
	 * ie, never have the same direction twice in a row.
	 * 
	 * @param instructions The set of instructions to optimize
	 */
	private LevelRouteInstruction[] optimizeInstructions(ArrayList<LevelRouteInstruction> instructions) {
		for (int i = 0; i < instructions.size() -1; ++i) {
			if (instructions.get(i).direction == instructions.get(i + 1).direction) {
				instructions.remove(i--);
			}
		}
		
		LevelRouteInstruction[] returnInstructions = new LevelRouteInstruction[instructions.size()];
		
		for (int i = 0; i < returnInstructions.length; ++i) {
			returnInstructions[i] = instructions.get(i);
		}
		
		return returnInstructions;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (LevelRouteInstruction instruction : routeInstructions) {
			builder.append(instruction.toString() + "\n");
		}
		
		return builder.toString();
	}
	
}
