package tsousa01.hw5;

public class HealthyHuman extends Human{

	static int total=0;
	public HealthyHuman(int xpos, int ypos) {
		super(xpos,ypos);
		total++;
	}

	public static int getTotalCases() {
		return total;
	}
}
