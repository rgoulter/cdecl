package cdecl;

import java.util.Scanner;

public class Driver {
	private final static String PROMPT = "cdecl> ";

	private enum Command {
		explain;

		public void run(String input) {
			String english = Explain.gibberishToEnglish(input);
			System.out.println(english);
			System.out.println();
		}
	}

	public static void main(String args[]) {
		System.out.println("Explain a phrase using \'explain <gibberish>\'");
		System.out.print(PROMPT);

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {
			String line = scanner.nextLine().trim();
			String[] lineParts = line.split("\\s", 2);
			String command = lineParts[0];

			Command c;
			try {
				c = Command.valueOf(command.toLowerCase());

				if (lineParts.length >= 2) {
					String input = lineParts[1];
					c.run(input);
				}
			} catch (IllegalArgumentException iaEx) {
				System.err.println("Invalid command: " + command);
			}

			System.out.print(PROMPT);
		}

		scanner.close();
	}
}
