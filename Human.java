package tsousa01.hw5;

abstract class Human {
	

	private int likely;
	private int xpos;
	private int ypos;
	
	public Human(int xpos, int ypos, int likely) {
		this.xpos=xpos;
		this.ypos=ypos;
		this.likely=likely;
	}
	
	public void move() {
		 String direction=Randomizer.getDirection((double)likely/100);
		 
		 if(direction.equals("stationary"))
			 return;
		 else
			 System.out.println(direction);
	}
}
