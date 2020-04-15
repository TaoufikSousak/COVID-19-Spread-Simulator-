package tsousa01.hw5;

public class RecoveredHuman extends Human{
	
	static int total=0;
	boolean takesMeasures;

	public RecoveredHuman(int xpos, int ypos) {
		this(xpos,ypos,false);
	}
	
	public RecoveredHuman(int xpos, int ypos, boolean measures) {
		super(xpos,ypos);
		total++;
		takesMeasures=measures;
	}

	public static int getTotalCases() {
		return total;
	}
	
	public boolean takesMeasures() {
		return takesMeasures;
	}
}
