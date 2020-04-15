package tsousa01.hw5;

abstract class Human implements Movable {

	private int xpos;
	private int ypos;
	private boolean measures;

	public Human(int xpos, int ypos, boolean measures) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.measures = measures;
	}

	public Human(int xpos, int ypos) {
		this(xpos, ypos, false);
	}
	
	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void moveTo(int newxpos, int newypos) {
		xpos = newxpos;
		ypos = newypos;
	}

	public int getTimeLeft() {
		return 0;
	} // will override in infected

	public boolean takesMeasures() {
		return false;
	}// will override in classes that use it

}
