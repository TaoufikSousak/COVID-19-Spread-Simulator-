package tsousa01.hw6;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SimulationDriver {

	public static void start() {

		Scanner scan = new Scanner(System.in);
		boolean error = true;

		int cities = 0; // same for all
		int time = 0; // same for all
		int infect = 0; // same for all
		int duration = 0; // same for all
		int fatal = 0; // same for all
		int howLikelyToMove = 0; // same for all

		int choice = 0; // helping variable
		String input; // helping variable

//arraye'd	   int width = 0;
//arraye'd	   int length = 0;	
//arraye'd     int people = 0;	
//arraye'd     int sick = 0;		
//arraye'd     int careful = 0;

///// new verion ////

		// parameters that are the same of all cities
		do {
			error = false;
			System.out.println("Regarding infectivity, deadliness, likelyness for people to move,");
			System.out.println("time it takes for a person to heal and simulation steps for every city:\n");
			System.out.print("Use default values from trusted sources / Proceed to custom settings (1/2): ");
			input = scan.nextLine();
			try {
				choice = Integer.parseInt(input);
				if (choice != 1 && choice != 2) {
					throw new NotPossibleAmountException("Can only choose '1' or '2'");
				}
			} catch (NumberFormatException e) {
				System.out.println("Can only choose '1' or '2'");
				error = true;
			} catch (NotPossibleAmountException e) {
				System.out.println(e.getMessage());
				error = true;
			}
		} while (error);
		System.out.println();

		if (choice == 1) {
			sick = 1;
			time = 500;
			fatal = 4;
			infect = 60;
			howLikelyToMove = 70;
			duration = 50; // how much time people need to recover and blocks to be disinfected
		} else {
			// steps
			do {
				error = false;
				System.out.print("Give the time (simulation steps) over 100 is recommended: ");
				input = scan.nextLine();
				try {
					time = Integer.parseInt(input);
					if (time < 0) {
						throw new NotPossibleAmountException("Must be positive");
					}

				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();

			// how fatal
			do {
				error = false;
				System.out.print("How fatal is the disease?(%): ");
				input = scan.nextLine();
				try {
					fatal = Integer.parseInt(input);
					if (fatal > 100 || fatal < 0) {
						throw new NotPossibleAmountException("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();

			// how contageous
			do {
				error = false;
				System.out.print("How contageous is the disease?(%): ");
				input = scan.nextLine();
				try {
					infect = Integer.parseInt(input);
					if (infect > 100 || infect < 0) {
						throw new NotPossibleAmountException("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();

			// get how long it takes patients to heal
			do {
				error = false;
				System.out.print("How long does it take for a patient to heal? (in simulation steps): ");
				input = scan.nextLine();
				try {
					duration = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				}
			} while (error);
			System.out.println();

			// get how likely is someone to move
			do {
				error = false;
				System.out.print("How likely is it for people to move per simulation step?(%): ");
				input = scan.nextLine();
				try {
					howLikelyToMove = Integer.parseInt(input);
					if (howLikelyToMove > 100 || howLikelyToMove < 0) {
						throw new NotPossibleAmountException("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();
		}

		// ask how many cities
		do {
			error = false;
			System.out.print("How many cities: ");
			input = scan.nextLine();
			try {
				time = Integer.parseInt(input);
				if (time < 0) {
					throw new NotPossibleAmountException("Must be positive");
				}

			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			} catch (NotPossibleAmountException e) {
				System.out.println(e.getMessage());
				error = true;
			}
		} while (error);
		System.out.println();

		// create arrays of elements different for every city
		int width[] = new int[cities];
		int length[] = new int[cities];
		int people[] = new int[cities];
		int infect[] = new int[cities];
		int careful[] = new int[cities];

////// old version ////		

		// get length
		do {
			error = false;
			System.out.print("Give the length: ");
			input = scan.nextLine();
			try {
				length = Integer.parseInt(input);
				if (length <= 0) {
					throw new NotPossibleAmountException("Must be over 0");
				}

			} catch (NumberFormatException e) {
				System.out.println("Must be an integer");
				error = true;
			} catch (NotPossibleAmountException e) {
				System.out.println(e.getMessage());
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
					throw new NotPossibleAmountException("Must be over 0");
				}

			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			} catch (NotPossibleAmountException e) {
				System.out.println(e.getMessage());
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
					throw new NotPossibleAmountException(
							"Cannot have more than " + length * width + " people or less than 0");
				}
			} catch (NumberFormatException e) {
				System.out.println("Must be a positive integer");
				error = true;
			} catch (NotPossibleAmountException e) {
				System.out.println(e.getMessage());
				error = true;
			}
		} while (error);
		System.out.println();

		// custom settings or not
		do {
			error = false;
			System.out.print("Use default values from trusted sources / Proceed to custom settings (1/2): ");
			input = scan.nextLine();
			try {
				choice = Integer.parseInt(input);
				if (choice != 1 && choice != 2) {
					throw new NotPossibleAmountException("Can only choose '1' or '2'");
				}
			} catch (NumberFormatException e) {
				System.out.println("Can only choose '1' or '2'");
				error = true;
			} catch (NotPossibleAmountException e) {
				System.out.println(e.getMessage());
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
						throw new NotPossibleAmountException(
								"Cannot have more than " + people + " sick people or less than 0");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);

			System.out.println();

			// get num of people that take measures
			do {
				error = false;
				System.out.print("Give the amount that take protective measures(like wear masks): ");
				input = scan.nextLine();
				try {
					careful = Integer.parseInt(input);
					if (careful > people - sick || careful < 0) {
						throw new NotPossibleAmountException(
								"Cannot have more than " + (people - sick) + " or less than 0");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();

			// get the time
			do {
				error = false;
				System.out.print("Give the time (simulation steps) over 100 is recommended: ");
				input = scan.nextLine();
				try {
					time = Integer.parseInt(input);
					if (time < 0) {
						throw new NotPossibleAmountException("Must be positive");
					}

				} catch (NumberFormatException e) {
					System.out.println("Must be a positive integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();
			// is the disease fatal
			do {
				error = false;
				System.out.print("How fatal is the disease?(%): ");
				input = scan.nextLine();
				try {
					fatal = Integer.parseInt(input);
					if (fatal > 100 || fatal < 0) {
						throw new NotPossibleAmountException("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);
			System.out.println();
			// get how infectious is the disease
			do {
				error = false;
				System.out.print("How contageous is the disease?(%): ");
				input = scan.nextLine();
				try {
					infect = Integer.parseInt(input);
					if (infect > 100 || infect < 0) {
						throw new NotPossibleAmountException("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);

			System.out.println();

			// get how long it takes patients to heal
			do {
				error = false;
				System.out.print("How long does it take for a patient to heal? (in simulation steps): ");
				input = scan.nextLine();
				try {
					duration = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				}
			} while (error);

			System.out.println();
			// get how likely is someone to move
			do {
				error = false;
				System.out.print("How likely is it for people to move per simulation step?(%): ");
				input = scan.nextLine();
				try {
					howLikelyToMove = Integer.parseInt(input);
					if (howLikelyToMove > 100 || howLikelyToMove < 0) {
						throw new NotPossibleAmountException("Must be between 0 and 100");
					}
				} catch (NumberFormatException e) {
					System.out.println("Must be an integer");
					error = true;
				} catch (NotPossibleAmountException e) {
					System.out.println(e.getMessage());
					error = true;
				}
			} while (error);

			System.out.println();

		} else {
			sick = 1;
			careful = (int) ((double) people * 0.5);
			time = 500;
			fatal = 4;
			infect = 60;
			howLikelyToMove = 70;
			duration = 50; // how much time people need to recover and blocks to be disinfected
		}

		// print out the details of the simulation
		System.out.print("\nCreated a board of dimentions " + length + "x" + width + " and " + people);

		if (people == 1)
			System.out.println(" person");
		else
			System.out.println(" people");
		System.out.print("The disease is " + fatal);

		System.out.println("% fatal and " + infect + "% infetcious");

		System.out.println("Initialy infected people: " + sick);

		System.out.println("The simulation will run for " + time + " steps");

		Crowd crowd = new Crowd(duration, width, length, people, howLikelyToMove, sick, fatal, infect, careful);

		while (time > 0 && people != (DeceasedHuman.getTotalCases() + RecoveredHuman.getTotalCases())) {
			crowd.move();
			time--;
		}
		crowd.move();

		System.out.println(
				"\n\nOF THE " + people + " INITIAL PEOPLE:\n" + InfectedHuman.getTotalCases() + " : GOT INFECTED\n"
						+ RecoveredHuman.getTotalCases() + " : RECOVERED\n" + DeceasedHuman.getTotalCases()
						+ " : PASSED AWAY\n" + (people - InfectedHuman.getTotalCases()) + " : NEVER GOT INFECTED");
		scan.close();
	}

}
