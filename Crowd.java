package tsousa01.hw5;

/**
 * The class responsible for connecting the humans wit the room, has move method
 * to move all the humans and updateStatus to update the status of them.
 * 
 * 
 * @author Taoufik Sousak, Loukas Papalazarou
 *
 */
public class Crowd {

	private Human[] humans;
	private static Room room;
	private double howLikelyToMove;
	private double fatal;
	private static double inf;
	private int duration;

	/**
	 * The only constructor, decides where to place each human, creates them and
	 * places them.
	 * 
	 * @param duration        how long it takes for someone to recover and for a
	 *                        room to get disinfected
	 * @param width           the width of the grid
	 * @param length          the length of the grid
	 * @param people          how many people there will be in general
	 * @param howLikelyToMove a percentage of how likely each human is to move
	 * @param sick            how many people are initially sick
	 * @param fatal           how fatal is the virus %
	 * @param infectius       how inectius is the virus
	 * @param caref           how many people take protective measures
	 */
	public Crowd(int duration, int width, int length, int people, int howLikelyToMove, int sick, int fatal,
			int infectius, int caref) {
		room = new Room(width, length, duration, infectius);
		humans = new Human[people];
		this.howLikelyToMove = (double) howLikelyToMove / 100;
		int xpos = 0;
		int ypos = 0;
		this.fatal = (double) fatal / 100;
		this.inf = (double) infectius / 100;
		this.duration = duration;
		// decide where to place human
		for (int i = 0; i < people; i++) {
			do {
				xpos = (int) (Math.random() * ((width)));
				ypos = (int) (Math.random() * ((length)));

			} while (room.isOccupied(ypos, xpos) != 0);

			// create sick humans
			if (i < sick) {

				humans[i] = new InfectedHuman(xpos, ypos, duration);
				room.occupy(ypos, xpos, 2);
			} else {
				boolean msrs = false;
				// put masks on specified amount
				if (i - sick < caref)
					msrs = true;
				humans[i] = new HealthyHuman(xpos, ypos, msrs);
				room.occupy(ypos, xpos, 1);
			}
			room.drawGrid();

		}
	}

	/**
	 * moves every human individaully, then calls update status to update their
	 * status and draws the grid.
	 */
	public void move() {

		for (int i = 0; i < humans.length; i++) {

			int newxpos = humans[i].getXpos();
			int newypos = humans[i].getYpos();
			String direction = null;
			// decide if human will move
			if (Randomizer.getBoolean(howLikelyToMove)) {
				room.occupy(humans[i].getYpos(), humans[i].getXpos(), 0);
				room.setMeasures(newypos, newxpos, false);
				// choose new available position
				do {
					// check if trapped
					if (noPossibleMove(humans[i].getXpos(), humans[i].getYpos()))
						break;

					newxpos = humans[i].getXpos();
					newypos = humans[i].getYpos();

					direction = Randomizer.getDirection();

					if (direction.contains("u") && humans[i].getYpos() != room.getLength() - 1)
						newypos++;
					if (direction.contains("d") && humans[i].getYpos() != 0)
						newypos--;
					if (direction.contains("r") && humans[i].getXpos() != room.getWidth() - 1)
						newxpos++;
					if (direction.contains("l") && humans[i].getXpos() != 0)
						newxpos--;
				} while (room.isOccupied(newypos, newxpos) != 0);

				humans[i].moveTo(newxpos, newypos);

				this.updateStatus(i, newxpos, newypos);
			}
		}
		room.drawGrid();

		try {
			Thread.sleep(150); // wait a second between every move to represent time passing
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * infects people that should get infected, recovers people that should recover
	 * and kills people that should die accordingly.
	 * 
	 * @param i
	 * @param newxpos
	 * @param newypos
	 */
	public void updateStatus(int i, int newxpos, int newypos) {
		//checks if infected human is resady to recover, recoveres or kills if its time
		if (humans[i] instanceof InfectedHuman)
			if (humans[i].getTimeLeft() == 0)
				if (!Randomizer.getBoolean(fatal))
					humans[i] = new RecoveredHuman(humans[i].getXpos(), humans[i].getYpos());
				else {
					humans[i] = new DeceasedHuman(humans[i].getXpos(), humans[i].getYpos());
					room.occupy(newypos, newxpos, 0);
				}
		//checks if healthy human should get infected
		if (humans[i] instanceof HealthyHuman) {
			room.occupy(newypos, newxpos, 1);

			if (possibleInfection(humans[i].getXpos(), humans[i].getYpos(), humans[i].takesMeasures()))
				if (humans[i].takesMeasures())
					humans[i] = new InfectedHuman(humans[i].getXpos(), humans[i].getYpos(), duration, true);
				else
					humans[i] = new InfectedHuman(humans[i].getXpos(), humans[i].getYpos(), duration);

		} else if (humans[i] instanceof InfectedHuman)
			room.occupy(newypos, newxpos, 2);
		else if (humans[i] instanceof RecoveredHuman)
			room.occupy(newypos, newxpos, 3);

		if (humans[i].takesMeasures())
			room.setMeasures(newypos, newxpos, true);
	}

	/**
	 * checks if human will get infected 
	 * @param x position
	 * @param y poition
	 * @param measures whether human takes protective measures
	 * @return true if human should get infected
	 */
	public static boolean possibleInfection(int x, int y, boolean measures) {
		
		if (humanTransmition(x, y, measures))
			return true;

		if (roomTransmition(x, y, measures))
			return true;

		return false;
	}

	/**
	 * checks if human is trapped
	 * @param x position
	 * @param y position
	 * @return true if human is trapped
	 */
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

	/**
	 * 
	 * @param x position
	 * @param y position
	 * @param measures whether human takes protective measures
	 * @return
	 */
	public static boolean roomTransmition(int x, int y, boolean measures) {
		double chance = inf / 2; // because its less likely to get virus from room than human
		if (measures)
			chance /= 2; // protective measures lower chance of infection
		if (room.isInfected(y, x) > 0)
			if (Randomizer.getBoolean(chance))
				return true;
		return false;
	}

	public static boolean humanTransmition(int x, int y, boolean measures) {

		double chance = inf;
		if (measures)
			chance /= 2; // protective measures lower chance of infection
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
