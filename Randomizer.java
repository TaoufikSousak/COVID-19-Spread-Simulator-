package tsousa01.hw5;

public class Randomizer {

	public static boolean getBoolean(double R) {
		if (Math.random() < R)
			return true;
		return false;
	}
	
	private static boolean willMove(double howLikelyToMove) {
		if (Math.random()>howLikelyToMove)
			return true;
		return false;
	}
	
	
	public static String getDirection(double howLikelyToMove) {
		
		if(!willMove(howLikelyToMove))
			return "stationary"; //for stationary
		
		double ran=Math.random();
		ran*=100;
		
		if(ran<12.5)
			return "u"; //for up
		else if(ran<25)
			return "ur"; //up right 
		else if(ran<37.5)
			return "r"; //right
		else if(ran<50)
			return "dr"; //down right
		else if(ran<62.5)
			return "d"; //down
		else if(ran<75)
			return "dl"; //down left
		else if(ran<87.5)
			return "l"; // left
		else if(ran<100)
			return "ul"; //up left
					
		
		return "error"; //for error
	}

}
