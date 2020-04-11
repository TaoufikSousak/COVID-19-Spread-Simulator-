package tsousa01.hw5;

import edu.princeton.cs.introcs.StdDraw;

public class Room {

	private int width;
	private int length;
	private boolean occupied[][];
	private boolean infected[][];
	private Crowd crowd;

	public Room(int width, int length) {
		this.width = width;
		this.length = length;
		occupied=new boolean[length][width];
		infected=new boolean[length][width];
		infected[1][1]=true;//trial
		this.drawGrid();
	}
	
	public boolean isInfected(int i,int j) {
		return infected[i][j];
	}
	
	public boolean isOccupied(int i,int j) {
		return occupied[i][j];
	}
	
	

	public void drawGrid() {
		StdDraw.setCanvasSize(800,800);
		
		StdDraw.setXscale(0, width); // afino ena perithosio gia na einai kathara orato olo to grid kai na min
										// ftanei sto telos tou kanva
		StdDraw.setYscale(0, length);

		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.setPenRadius(0.2 / ((width+length)/2)); // ekana dokimes gia na vro to sosto megethos penas

		for (int j = 0; j <= length; j++) // zografizo to grid
			StdDraw.line(0, j, width, j);
		for (int j = 0; j <= width; j++)
			StdDraw.line(j, 0, j, length);
		
		StdDraw.setPenColor(StdDraw.RED);
		
		for(int i=length; i>=0; i--) {
			for(int j=0; j<=width; j++) {
//				StdDraw.filledRectangle(j, i, halfWidth, halfHeight)
			}
		}
	}

}
