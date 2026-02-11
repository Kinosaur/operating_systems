import java.util.Scanner;

public class BankersAlgorithmApp {

    static final String INPUT_FILENAME = "input1.txt";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String continueChoice;

        do {
            System.out.println("  Banker's Algorithm: Resource Manager  ");
            System.out.println("========================================");

            // 1. Get Setup Info
            System.out.print("Enter Resource size (m): ");
            int m = validateInt(console);

            System.out.print("Enter Process size (n): ");
            int n = validateInt(console);

            // 2. Initialize State
            BankersState state = new BankersState(n, m);
            boolean isDataLoaded = false;

            // 3. Load Data (File or Random)
            System.out.print("\nEnter 'I' to load from '" + INPUT_FILENAME + "' or any other key for Random data: ");
            String choice = console.next();

            if (choice.equalsIgnoreCase("I")) {
                if (InputManager.loadFromFile(INPUT_FILENAME, state)) {
                    isDataLoaded = true;
                } else {
                    System.out.println("Data load failed. Please check the file.");
                }
            } else {
                InputManager.generateRandomData(state);
                isDataLoaded = true;
            }

            // 4. Perform Calculations & Display
            if (isDataLoaded) {
                state.calculateNeed();
                state.calculateAvailable();

                DisplayManager.printSystemState(state);
                state.runSafetyAlgorithm();
            }

            // 5. Ask to Continue
            System.out.print("\nPress Y to continue or any key to Exit: ");
            continueChoice = console.next();
            System.out.println("\n");

        } while (continueChoice.equalsIgnoreCase("Y"));

        System.out.println("System Exited. Bye!!");
        console.close();
    }

    private static int validateInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}