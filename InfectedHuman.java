package tsousa01.hw5;

public class InfectedHuman extends Human {

	int timeLeft;

	public InfectedHuman(int xpos, int ypos, int duration) {
		super(xpos, ypos);
		timeLeft = duration;
	}

	public void moveTo(int newxpos, int newypos) {
		super.moveTo(newxpos, newypos);
		timeLeft--;
	}

	public int getTimeLeft() {
		return timeLeft;
	}



	



	

}
