package tsousa01.hw5;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SimulationDriver {

	public static void start() {

		Scanner scan = new Scanner(System.in);
		boolean done = false;

		int width = 0;
		int length = 0;

		try {
				done = true;
				System.out.print("Width of room: ");
				width = scan.nextInt();
				System.out.print("Wength of room: ");
				length = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.print("You need to give 2 integers, try again");
				done = false;
			}

		Room room = new Room(width, length);
	}



}
