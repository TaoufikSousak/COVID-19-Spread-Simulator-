package tsousa01.hw5;

public class RecoveredHuman extends Human{
	
	static int total=0;
	
	public RecoveredHuman(int xpos, int ypos) {
		super(xpos,ypos);
		total++;
	}

	public static int getTotalCases() {
		return total;
	}
	
}
