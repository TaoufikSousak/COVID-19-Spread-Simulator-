package tsousa01.hw5;

public class Crowd {

	private Human[] humans;
	private static Room room;
	private double howLikelyToMove;
	private double fatal;
	private static double inf;
	private int duration;

	public Crowd(int duration, int width, int length, int people, int howLikelyToMove, int sick, 
			int fatal, int infectius, int caref) {
		room = new Room(width, length, duration, infectius);
		humans = new Human[people];
		this.howLikelyToMove = (double) howLikelyToMove / 100;
		int xpos = 0;
		int ypos = 0;
		this.fatal = (double) fatal / 100;
		this.inf = (double) infectius / 100;
		this.duration = duration;

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
				boolean msrs=false;
				if(i<caref)
					msrs=true;
				humans[i] = new HealthyHuman(xpos, ypos, msrs);
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
				room.setMeasures(newypos, newxpos, false);
				do {

					if (noPossibleMove(humans[i].getXpos(), humans[i].getYpos()))
						break;

					newxpos = humans[i].getXpos();
					newypos = humans[i].getYpos();

					direction = Randomizer.getDirection();
					// System.out.println("direction: " + direction + " contains u: " + // only for
					// testing
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

			if (humans[i] instanceof InfectedHuman)
				if (humans[i].getTimeLeft() == 0)
					if (!Randomizer.getBoolean(fatal))
						humans[i] = new RecoveredHuman(humans[i].getXpos(), humans[i].getYpos());
					else {
						humans[i] = new DeceasedHuman(humans[i].getXpos(), humans[i].getYpos());
						room.occupy(newypos, newypos, 0);
					}

			if (humans[i] instanceof HealthyHuman) {	
				room.occupy(newypos, newxpos, 1);
				if(humans[i].takesMeasures())
					room.setMeasures(newypos, newxpos, true);
				
				if (possibleInfection(humans[i].getXpos(), humans[i].getYpos(), humans[i].takesMeasures()))
					if(humans[i].takesMeasures()) 
						humans[i] = new InfectedHuman(humans[i].getXpos(), humans[i].getYpos(), duration, true);
					else
						humans[i] = new InfectedHuman(humans[i].getXpos(), humans[i].getYpos(), duration);

				

			} else if (humans[i] instanceof InfectedHuman)
				room.occupy(newypos, newxpos, 2);
			else if (humans[i] instanceof RecoveredHuman)
				room.occupy(newypos, newxpos, 3);

		}
		room.drawGrid();

		try {
			Thread.sleep(150); // wait a second between every move to represent time passing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean possibleInfection(int x, int y, boolean measures) {
		if (humanTransmition(x, y, measures))
			return true;

		if (roomTransmition(x, y, measures))
			return true;

		return false;
	}

	public static boolean noPossibleMove(int x, int y) {

		int cnt = 0;
		try {
			if (room.isOccupied(y - 1, x + 1) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y, x + 1) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y + 1, x + 1) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y - 1, x) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y + 1, x) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y - 1, x - 1) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y, x - 1) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y + 1, x - 1) > 0)
				cnt++;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return (cnt == 8);
	}

	public static boolean roomTransmition(int x, int y, boolean measures) {
		double chance=inf/2; // because its less likely to get virus from room than human
		if(measures)
			chance/=2;	//protective measures lower chance of infection
		if (room.isInfected(y, x) > 0)
			if (Randomizer.getBoolean(chance))
				return true;
		return false;
	}

	public static boolean humanTransmition(int x, int y, boolean measures) {
		
		double chance=inf;
		if(measures)
			chance/=2; //protective measures lower chance of infection
		try {
			if (room.isOccupied(y - 1, x + 1) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y, x + 1) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y + 1, x + 1) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y - 1, x) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y + 1, x) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y - 1, x - 1) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y, x - 1) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			if (room.isOccupied(y + 1, x - 1) == 2)
				if (Randomizer.getBoolean(chance))
					return true;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return false;
	}
}
