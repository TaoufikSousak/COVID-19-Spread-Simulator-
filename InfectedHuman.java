package tsousa01.hw5;

public class InfectedHuman extends Human {

	private int timeLeft;
	static int  total=0;
	private boolean measures;
	
	public InfectedHuman(int xpos, int ypos, int duration, boolean measures) {
		super(xpos, ypos);
		timeLeft = duration;
		this.measures=measures;
		total++;
	}
	
	public InfectedHuman(int xpos, int ypos, int duration) {
		this(xpos,ypos,duration,false);
	}

	public void moveTo(int newxpos, int newypos) {
		super.moveTo(newxpos, newypos);
		timeLeft--;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public boolean takesMeasures() {
		return measures;
	}

	public static int getTotalCases() {
		return total;
	}
	



	

}
