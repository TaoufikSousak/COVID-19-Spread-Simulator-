package tsousa01.hw5;

public class DeceasedHuman extends Human {

	static int total=0;
	
	public DeceasedHuman(int xpos, int ypos) {
		super(xpos, ypos);
		total++;
	}
	
	public static int getTotalCases() {
		return total;
	}
	
	
}
