package tsousa01.hw5;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SimulationDriver {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		boolean done = false;

		int width = 0;
		int length = 0;

		try {
				done = true;
				System.out.println("width of room: ");
				width = scan.nextInt();
				System.out.println("length of room: ");
				length = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("you need to give 2 integers, try again");
				done = false;
			}

		Room room = new Room(width, length);
	}

}
