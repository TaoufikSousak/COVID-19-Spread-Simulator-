package tsousa01.hw5;

public class InfectedHuman extends Human {

	int timeLeft;
	static int  total=0;
	
	public InfectedHuman(int xpos, int ypos, int duration, boolean measures) {
		super(xpos, ypos);
		timeLeft = duration;
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


	public static int getTotalCases() {
		return total;
	}
	



	

}
