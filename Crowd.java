package tsousa01.hw5;

public class Crowd {

	private Human[] humans;
	private Room room;

	public Crowd(int duration, int width, int length, int people) {
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

			humans[i] = new HealthyHuman(xpos, ypos);
			room.occupy(ypos, xpos); // ypos <--> xpos because the methods in Room work with (int y, int x)
		}
		room.drawGrid();

	}

}
