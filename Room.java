package tsousa01.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Room {

	private int width;
	private int length;
	private boolean occupied[][];
	private int infected[][];
	private int duration;

	public Room(int width, int length, int duration) {
		this.width = width;
		this.length = length;
		occupied = new boolean[length][width];
		infected = new int[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				occupied[i][j] = false;
				infected[i][j] = 0;
			}
		}
		this.drawGrid();
	}

	public int isInfected(int y, int x) {
		return infected[y][x];
	}

	public boolean isOccupied(int y, int x) {
		return occupied[y][x];
	}

	public void infect(int y, int x) {
		infected[y][x] = duration;
	}

	public void occupy(int y, int x) {
		occupied[y][x] = true;
	}

	public void drawGrid() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(length, 0);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.setPenRadius(0.2 / ((width + length) / 2));

		// draw lines
		for (int y = 0; y <= length; y++) {
			StdDraw.line(0, y, width, y);
		}
		for (int x = 0; x <= width; x++) {
			StdDraw.line(x, 0, x, length);
		}

		infected[0][0] = 10;// test
		occupied[0][0]=true; //test
		System.out.println(isInfected(0, 0));

		for (double y = 0.5; y < length; y++) {
			for (double x = 0.5; x < width; x++) {
				if (isInfected((int) y, (int) x) > 0) {
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledRectangle(x, y, 0.47, 0.47);
				}
				if (isOccupied((int) x, (int) y)) {
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.filledCircle(x, y, 0.25);
				}

			}
		}

	}

}