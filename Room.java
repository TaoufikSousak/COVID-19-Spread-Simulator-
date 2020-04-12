package tsousa01.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Room {

	private int width;
	private int length;
	private int occupied[][]; // 0=no | 1=with healthy human | 2=with infected human | 3=with recovered human
	private int infected[][];
	private int duration;

	public Room(int width, int length, int duration) {
		this.width = width;
		this.length = length;
		occupied = new int[length][width];
		infected = new int[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				occupied[i][j] = 0;
				infected[i][j] = 0;
			}
		}
		this.drawGrid();
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int isInfected(int y, int x) {
		return infected[y][x];
	}

	public int isOccupied(int y, int x) {
		return occupied[y][x];
	}

	public void infect(int y, int x) {
		infected[y][x] = duration;
	}

	public void occupy(int y, int x, int status) {
		occupied[y][x] = status;
	}

	public void drawGrid() {
		StdDraw.setCanvasSize(800, 700);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(length, 0);
		int rad;

		// draw infected spaces and people
		for (double y = 0.5; y < length; y++) {
			for (double x = 0.5; x < width; x++) {
				if (isInfected((int) y, (int) x) > 0) {
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledRectangle(x, y, 0.5, 0.5);
				}
				if (length > width)
					rad = length;
				else
					rad = width;
				if (isOccupied((int) y, (int) x)==1) {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.setPenRadius(0.255 / (rad * 0.3));
					StdDraw.point(x, y);
				}

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

}