package tsousa01.hw5;

import java.util.Scanner;
import java.util.InputMismatchException;

public class SimulationDriver {

	public static void start() {

		Scanner scan = new Scanner(System.in);
		boolean error = true;

		int width = 0;
		int length = 0;
		int time=0;
		String input;

		while (error) {
			error = false;
			System.out.print("Give the length: ");	
			input =scan.nextLine();
			try {
				length=Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("Give a positive integer: ");
				error = true;
			}
		}
		
		error=true;
		
		while (error) {
			error = false;
			System.out.print("Give the width: ");	
			input =scan.nextLine();
			try {
				width=Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("Give a positive integer: ");
				error = true;
			}
		}
		
		error=true;
		while (error) {
			error = false;
			System.out.print("Give the time (in minutes): ");	
			input =scan.nextLine();
			try {
				time=Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("Give a positive integer: ");
				error = true;
			}
		}

		Room room = new Room(width, length);
	}

}
