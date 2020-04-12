package tsousa01.hw5;

public class Crowd {

	private Human[] humans;
	private Room room;
	private double howLikelyToMove;

	public Crowd(int duration, int width, int length, int people, int howLikelyToMove) {
		room = new Room(width, length, duration);
		humans = new Human[people];
		this.howLikelyToMove = (double) howLikelyToMove / 100;
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

	public void move() {

		for (int i = 0; i < humans.length; i++) {

			int newxpos = humans[i].getXpos();
			int newypos = humans[i].getYpos();
			String direction = null;
			if (Randomizer.getBoolean(howLikelyToMove))
				while (room.isOccupied(newypos, newxpos)) {

					newxpos = humans[i].getXpos();
					newypos = humans[i].getYpos();

					direction = Randomizer.getDirection();

					if (direction.contains("u") && humans[i].getYpos()!=room.getLength())
						newypos++;
					if (direction.contains("d") && humans[i].getYpos()!=0)
						newypos--;
					if (direction.contains("r") && humans[i].getXpos()!=room.getWidth())
						newxpos++;
					if (direction.contains("l") && humans[i].getYpos()!=0)
						newypos--;
				}

			humans[i].moveTo(newxpos, newypos);

		}

		try {
			Thread.sleep(1000); // wait a second between every move to represent time passing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
