import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("PAGE REPLACEMENT ALGORITHMS SIMULATION");
        System.out.println("========================================");

        // 1. Get Page Reference String
        System.out.print("Enter a Page Reference string without space <Max 20>: ");
        String refStringInput = scanner.nextLine();
        // Validation could be added here for Max 20

        // 2. Get Memory Frame Size
        System.out.print("Enter the size of Memory Frame <Max 10>: ");
        int frameSize = scanner.nextInt();

        int choice = 0;
        while (choice != 4) {
            System.out.println("\nAlgorithm Option Menu:");
            System.out.println("====================");
            System.out.println("1. OPTIMAL");
            System.out.println("2. LRU");
            System.out.println("3. FIFO");
            System.out.println("4. Exit simulation");
            System.out.print("\nSelect an OPTION from above by entering its number: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Running OPTIMAL simulation...");
                    // Logic for OPTIMAL will go here
                    break;
                case 2:
                    System.out.println("Running LRU simulation...");
                    // Logic for LRU will go here
                    break;
                case 3:
                    System.out.println("Running FIFO simulation...");
                    // Logic for FIFO will go here
                    break;
                case 4:
                    System.out.println("Exiting simulation. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}