package tsousa01.hw5;

public class Crowd {

	private Human[] humans;
	private Room room;

	public Crowd(int duration, int width, int length, int people, int howLikelyToMove) {
		room = new Room(width, length, duration);
		humans = new Human[people];
		int xpos = 0;
		int ypos = 0;
		for (int i = 0; i < people; i++) {
			do {
				xpos = (int) (Math.random() * ((width)));
				ypos = (int) (Math.random() * ((length)));

			} while (room.isOccupied(ypos, xpos)); // ypos <--> xpos because the methods in Room work with (int y, int
													// x)

			humans[i] = new HealthyHuman(xpos, ypos, howLikelyToMove);
			room.occupy(ypos, xpos); // ypos <--> xpos because the methods in Room work with (int y, int x)
		}
		room.drawGrid();

	}
	
	public void move() {
		
		for(int i=0; i<humans.length; i++)
			humans[i].move();
		
		
		try {
			Thread.sleep(1000);	// wait a second between every move to represent time passing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
