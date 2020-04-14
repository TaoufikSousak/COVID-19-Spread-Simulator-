package tsousa01.hw5;

public class HealthyHuman extends Human{

	static int total=0;
	boolean takesMeasures;
	
	public HealthyHuman(int xpos, int ypos) {
		this(xpos, ypos, false);
	}

	public HealthyHuman(int xpos, int ypos, boolean careful) {
		super(xpos,ypos);
		takesMeasures=careful;
		total++;
	}
	
	public static int getTotalCases() {
		return total;
	}
	
	public boolean takesMeasures() {
		return takesMeasures;
	}
}
