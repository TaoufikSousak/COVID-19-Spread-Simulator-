package tsousa01.hw5;

public class Crowd {

	private Human[] humans;
	private Room room;
	private double howLikelyToMove;

	public Crowd(int duration, int width, int length, int people, int howLikelyToMove, int sick) {
		room = new Room(width, length, duration);
		humans = new Human[people];
		this.howLikelyToMove = (double) howLikelyToMove / 100;
		int xpos = 0;
		int ypos = 0;

		for (int i = 0; i < people; i++) {
			do {
				xpos = (int) (Math.random() * ((width)));
				ypos = (int) (Math.random() * ((length)));

			} while (room.isOccupied(ypos, xpos) != 0); // ypos <--> xpos because the methods in Room work with (int y,
														// int
														// x)
			if (i < sick) {
				humans[i] = new InfectedHuman(xpos, ypos, duration);
				room.occupy(ypos, xpos, 2);
			} else {
				humans[i] = new HealthyHuman(xpos, ypos);
				room.occupy(ypos, xpos, 1); // ypos <--> xpos because the methods in Room work with (int y, int x)}
			}
			room.drawGrid();

		}
	}

	public void move() {

		for (int i = 0; i < humans.length; i++) {

			int newxpos = humans[i].getXpos();
			int newypos = humans[i].getYpos();
			String direction = null;
			if (Randomizer.getBoolean(howLikelyToMove)) {
				room.occupy(humans[i].getYpos(), humans[i].getXpos(), 0);
				do {

					newxpos = humans[i].getXpos();
					newypos = humans[i].getYpos();

					direction = Randomizer.getDirection();
					// System.out.println("direction: " + direction + " contains u: " +
					// direction.contains("u"));
					if (direction.contains("u") && humans[i].getYpos() != room.getLength() - 1) {
						newypos++;
						// System.out.println("ITS AN UP");
					}
					if (direction.contains("d") && humans[i].getYpos() != 0)
						newypos--;
					if (direction.contains("r") && humans[i].getXpos() != room.getWidth() - 1)
						newxpos++;
					if (direction.contains("l") && humans[i].getXpos() != 0)
						newxpos--;
				} while (room.isOccupied(newypos, newxpos) != 0);
			}
			humans[i].moveTo(newxpos, newypos);
			if (humans[i] instanceof HealthyHuman)
				room.occupy(newypos, newxpos, 1);
			else if (humans[i] instanceof InfectedHuman)
				room.occupy(newypos, newxpos, 2);
			else if (humans[i] instanceof RecoveredHuman)
				room.occupy(newypos, newxpos, 3);

		}
		room.drawGrid();

		try {
			Thread.sleep(500); // wait a second between every move to represent time passing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
