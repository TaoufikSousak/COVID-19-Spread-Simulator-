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
		int infect = 0;
		int sick = 0;
		int choice = 0;
		boolean grid = true;
		boolean fatal;
		String input;

		// get length
		do {
			error = false;
			System.out.print("Give the length: ");
			input = scan.nextLine();
			try {
				length = Integer.parseInt(input);
				if (length <= 0) {
					error = true;
					System.out.println("Must be positive");
				}

			} catch (NumberFormatException e) {
				System.out.println("Must be an integer");
				error = true;
			}
		} while (error);
		System.out.println();
		// get width
		do {
			error = false;
			System.out.print("Give the width: ");
			input = scan.nextLine();
			try {
				width = Integer.parseInt(input);
				if (width <= 0) {
					error = true;
					System.out.println("Must be positive");
				}

			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);
		System.out.println();
		// get the amount of people
		do {
			error = false;
			System.out.print("Give the amount of people: ");
			input = scan.nextLine();
			try {
				people = Integer.parseInt(input);
				if (people > length * width || people < 0) {
					System.out.println("Cannot have more than " + length * width + " people or less than 0");
					error = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);
		System.out.println();

		// custom settings or not
		do {
			error = false;
			System.out.print("Use default settings / Proceed to advanced (1/2): ");
			input = scan.nextLine();
			try {
				choice = Integer.parseInt(input);
				if (choice != 1 && choice != 2) {
					System.out.println("Can only choose '1' or '2'");
					error = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			}
		} while (error);
		System.out.println();

		if (choice == 2) {
			// get number of initially sick people
			do {
				error = false;
				System.out.print("Give the amount of INITIALLY INFECTED people: ");
				input = scan.nextLine();
				try {
					sick = Integer.parseInt(input);
					if (sick > people || sick < 0) {
						System.out.println("Cannot have more than " + people + " sick people or less than 0");
						error = true;
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				}
			} while (error);
			System.out.println();

			// get the time
			do {
				error = false;
				System.out.print("Give the time (in minutes): ");
				input = scan.nextLine();
				try {
					time = Integer.parseInt(input);
					if (time < 0) {
						error = true;
						System.out.println("Must be positive");
					}

				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				}
			} while (error);
			System.out.println();
			// is the disease fatal
			do {
				error = false;
				System.out.print("Is the disease fatal?(y/n): ");
				input = scan.nextLine();
				if (!input.equals("Y") && !input.equals("y") && !input.equals("n") && !input.equals("N")) {
					error = true;
					System.out.println("Must be either 'y' or 'n'");
				}

			} while (error);
			System.out.println();
			// get how infectious is the disease
			do {
				error = false;
				System.out.print("How infectious is the disease?(%): ");
				input = scan.nextLine();
				try {
					infect = Integer.parseInt(input);
					if (infect > 100 || infect < 0) {
						error = true;
						System.out.println("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				}
			} while (error);
			if (input.equals("Y") || input.equals("y"))
				fatal = true;
			else
				fatal = false;
			System.out.println();
			//do you want the grid to be drawn
			do {
				error = false;
				System.out.print("Draw simulation with/without grid? (1/2): ");
				input = scan.nextLine();
				try {
					choice = Integer.parseInt(input);
					if (choice != 1 && choice != 2) {
						error = true;
						System.out.println("Must be either '1' or '2'");
					}

				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				}

			} while (error);

			if (choice == 1)
				grid = true;

		} else {
			sick = (int) ((double) people * 0.1);
			time = 100;
			fatal = true;
			infect = 80;
		}

		// print out the details of the simulation
		System.out.print("\nCreated a board of dimentions " + length + "x" + width + " and " + people);

		if (people == 1)
			System.out.println(" person");
		else
			System.out.println(" people");
		System.out.print("The disease is ");
		if (fatal)
			System.out.print("non-");
		System.out.println("fatal and " + infect + "% infetcious");

		System.out.println("Initialy infected people: " + sick);

		System.out.println("The simulation will run for " + time + " steps");

		int howLikelyToMove = 50;
		int duration = 10; // how much time people need to recover and blocks to be dissinfected
		Crowd crowd = new Crowd(duration, width, length, people, howLikelyToMove, sick, grid);

		while (time > 0) {
			crowd.move();
			time--;
		}

	}

}
