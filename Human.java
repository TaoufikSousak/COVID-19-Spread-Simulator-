package tsousa01.hw5;

abstract class Human implements Movable {

	private int xpos;
	private int ypos;

	public Human(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
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

}
