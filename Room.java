package tsousa01.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Room {

	private int width;
	private int length;
	private int occupied[][]; // 0=no | 1=with healthy human | 2=with infected human | 3=with recovered human
	private int infected[][];
	private int duration;
	private boolean drawgrid;
	private double inf;
	private boolean measures[][];

	public Room(int width, int length, int duration, int infectious) {
		this.width = width;
		this.length = length;
		occupied = new int[length][width];
		infected = new int[length][width];
		measures= new boolean[length][width];
		this.duration=duration;
		this.inf=(double) infectious/100;
		inf/=3;		//less likely than human transmition
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				occupied[i][j] = 0;
				infected[i][j] = 0;
				measures[i][j]=false;
			}
		}
		this.gridSetup();
		this.drawGrid();
	}

	public void setMeasures(int y, int x, boolean measures) {
		this.measures[y][x]=measures;
	}
	
	public boolean takesMeasures(int y, int x) {
		return measures[y][x];
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

	public void disinfect(int y, int x) {
		infected[y][x]--;
	}
	public void occupy(int y, int x, int status) {
		occupied[y][x] = status;
	}

	private void gridSetup() {
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(800, 700);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(length, 0);
	}

	private void drawMask(double y, double x) {
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		double rect_x[] = new double[4];
		double rect_y[] = new double[4];
		rect_x[0] = x;
		rect_y[0] = y;

		rect_x[1] = x + 0.1;
		rect_y[1] = y - 0.1;

		rect_x[2] = x + 0.2;
		rect_y[2] = y + 0.1;

		rect_x[3] = x + 0.1;
		rect_y[3] = y - 0.1;
		StdDraw.filledRectangle(x, y + 0.12, 0.18, 0.1);
		StdDraw.filledPolygon(rect_x, rect_y);

	}

	public void drawGrid() {
		int rad;
		StdDraw.show(80);
		StdDraw.clear();
		// draw infected spaces and people
		for (double y = 0.5; y < length; y++) {
			for (double x = 0.5; x < width; x++) {
				if (isInfected((int) y, (int) x) > 0) {
					StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE.brighter());
					StdDraw.filledRectangle(x, y, 0.5, 0.5);
					this.disinfect((int) y, (int) x);
				}
				if (length > width)
					rad = length;
				else
					rad = width;
				if (isOccupied((int) y, (int) x) != 0) {
					if (isOccupied((int) y, (int) x) == 1)
						StdDraw.setPenColor(StdDraw.BLUE);
					else if (isOccupied((int) y, (int) x) == 2) {
						StdDraw.setPenColor(StdDraw.RED);
						possibleInfection((int)y,(int)x);
					}
					else if (isOccupied((int) y, (int) x) == 3)
						StdDraw.setPenColor(StdDraw.GREEN);

					StdDraw.setPenRadius(0.255 / (rad * 0.3));
					StdDraw.point(x, y);
				}
				if(takesMeasures((int) y, (int) x))
					this.drawMask(y, x);
					

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
	
	public void possibleInfection(int y, int x) {
		if(Randomizer.getBoolean(inf))
			this.infect(y, x);
	}

}