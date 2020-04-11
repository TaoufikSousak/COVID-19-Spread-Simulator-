package tsousa01.hw5;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SimulationDriver {

	public static void start() {

		Scanner scan = new Scanner(System.in);
		boolean error = true;

		int width = 0;
		int length = 0;
		int time = 0;
		int people = 0;
		boolean fatal;
		String input;

		do {
			error = false;
			System.out.print("Give the length: ");
			input = scan.nextLine();
			try {
				length = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);

		do {
			error = false;
			System.out.print("Give the width: ");
			input = scan.nextLine();
			try {
				width = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);

		do {
			error = false;
			System.out.print("Give the time amount of people: ");
			input = scan.nextLine();
			try {
				people = Integer.parseInt(input);
				if (people > length * width) {
					System.out.println("Cannot have more people than available spaces");
					error = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);

		do {
			error = false;
			System.out.print("Give the time (in minutes): ");
			input = scan.nextLine();
			try {
				time = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);
		
		do {
			error = false;
			System.out.print("Is the disease fatal? ");
			input = scan.nextLine();
			try {
				fatal = Boolean.parseBoolean(input);
			} catch (NumberFormatException e) {
				System.out.println("Must be a true or false");
				error = true;
			}
		} while (error);

		System.out.println("Created a board of dimentions ");

		Room room = new Room(width, length);
	}

}
