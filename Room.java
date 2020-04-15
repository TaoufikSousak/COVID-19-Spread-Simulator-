package tsousa01.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Room {

	private int width;
	private int length;
	private int occupied[][]; // 0=no | 1=with healthy human | 2=with infected human | 3=with recovered human
	private int infected[][];
	private int duration;
	private double inf;
	private boolean measures[][];

	/**
	 * 
	 * @param width of grid
	 * @param length of grid
	 * @param duration is the amount of time required for a person to heal 
	 * @param infectious how likely is for people to contract the virus from other infected people or spaces
	 */
	public Room(int width, int length, int duration, int infectious) {
		this.width = width;
		this.length = length;
		occupied = new int[length][width];
		infected = new int[length][width];
		measures= new boolean[length][width];
		this.duration=duration;
		this.inf=(double) infectious/100;
		inf/=3;		//less likely than human transmission
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				occupied[i][j] = 0;
				infected[i][j] = 0;
				measures[i][j]=false;
			}
		}
		//setup of grid
		this.gridSetup();
		this.drawGrid();
	}

	/**
	 * 
	 * @param y position
	 * @param x position
	 * @param measures sets whether the person on that position is taking protective measures
	 */
	public void setMeasures(int y, int x, boolean measures) {
		this.measures[y][x]=measures;
	}
	
	/**
	 * 
	 * @param y position
	 * @param x position
	 * @return returns whether the person on that position is taking protective measures
	 */
	public boolean takesMeasures(int y, int x) {
		return measures[y][x];
	}
	
	/**
	 * 
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @param y position
	 * @param x position
	 * @return true if the space in that position is infected
	 */
	public int isInfected(int y, int x) {
		return infected[y][x];
	}

	/**
	 * 
	 * @param y position
	 * @param x position 
	 * @return true if the space on that position has a person on it
	 */
	public int isOccupied(int y, int x) {
		return occupied[y][x];
	}

	/**
	 * 
	 * @param y position
	 * @param x position
	 * 
	 * Sets specified position to be infected
	 */
	public void infect(int y, int x) {
		infected[y][x] = duration;
	}

	/**
	 * 
	 * @param y position
	 * @param x position
	 * 
	 * Sets specified position to not be infected
	 */
	public void disinfect(int y, int x) {
		infected[y][x]--;
	}
	
	/**
	 * 
	 * @param y position
	 * @param x position
	 * @param status is set to the specified position
	 */
	public void occupy(int y, int x, int status) {
		occupied[y][x] = status;
	}

	/**
	 * Grid setup
	 */
	private void gridSetup() {
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(800, 700);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(length, 0);
	}

	/**
	 * 
	 * @param y position
	 * @param x position
	 * @param size of mask
	 * 
	 * Draws a mask on the specified position
	 */
	private void drawMask(double y, double x,double size) {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.setPenRadius(size/4);
		StdDraw.line(x-0.1, y+0.12, x+0.1, y+0.12);
		StdDraw.line(x-0.1, y+0.14, x+0.1, y+0.14);
		StdDraw.line(x-0.1, y+0.16, x+0.1, y+0.16);
		StdDraw.setPenRadius(size/12);
		StdDraw.line(x+0.1, y+0.1, x+0.27, y);
		StdDraw.line(x-0.1, y+0.1, x-0.27, y);
	}

	/**
	 * This method draws the updated grid every time it is called 
	 */
	public void drawGrid() {
		double rad;
		StdDraw.show(100);
		StdDraw.clear();
		// draw infected spaces and people
		for (double y = 0.5; y < length; y++) {
			for (double x = 0.5; x < width; x++) {
				if (isInfected((int) y, (int) x) > 0) {
					StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE.brighter());
					StdDraw.filledRectangle(x, y, 0.5, 0.5);
					this.disinfect((int) y, (int) x);
				}
				//pen radius is depended on the smallest of width and length
				if (length > width)
					rad = length;
				else
					rad = width;
				//draws people with the color that matches their status
				if (isOccupied((int) y, (int) x) != 0) {
					if (isOccupied((int) y, (int) x) == 1)
						StdDraw.setPenColor(StdDraw.BLUE);
					else if (isOccupied((int) y, (int) x) == 2) {
						StdDraw.setPenColor(StdDraw.RED);
						possibleInfection((int)y,(int)x);
					}
					else if (isOccupied((int) y, (int) x) == 3)
						StdDraw.setPenColor(StdDraw.GREEN);

					rad=0.255 / (rad * 0.3);
					StdDraw.setPenRadius(rad);
					StdDraw.point(x, y);
				}
				//draws mask
				if(takesMeasures((int) y, (int) x))
					this.drawMask(y, x,rad);
					

			}
		}

		// draw lines
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.setPenRadius(0.2 / ((width + length) / 2));
			for (int y = 0; y <= length; y++) {
				StdDraw.line(0, y, width, y);
			}
			for (int x = 0; x <= width; x++) {
				StdDraw.line(x, 0, x, length);
			}
		

	}
	
	/**
	 * 
	 * @param y position
	 * @param x position
	 * 
	 * This method can possibly infect the specified position when called
	 * This is dependent of the inf variable and random chance
	 */
	public void possibleInfection(int y, int x) {
		if(Randomizer.getBoolean(inf))
			this.infect(y, x);
	}

}