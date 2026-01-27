import java.util.Scanner;

public class BankersAlgorithmApp {

    static final String INPUT_FILENAME = "input.txt";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("  Banker's Algorithm: Resource Manager  ");
        System.out.println("========================================");

        // 1. Get Setup Info
        System.out.print("Enter Resource size (m): ");
        int m = console.nextInt();

        System.out.print("Enter Process size (n): ");
        int n = console.nextInt();

        // 2. Initialize State
        BankersState state = new BankersState(n, m);

        // 3. Load Data (File or Random)
        System.out.print("\nEnter 'I' to load from '" + INPUT_FILENAME + "' or any other key for Random data: ");
        String choice = console.next();

        if (choice.equalsIgnoreCase("I")) {
            boolean success = InputManager.loadFromFile(INPUT_FILENAME, state);
            if (!success) return;
        } else {
            InputManager.generateRandomData(state);
        }

        // 4. Perform Calculations
        state.calculateNeed();
        state.calculateAvailable();

        // 5. Display Result
        DisplayManager.printSystemState(state);
    }
}