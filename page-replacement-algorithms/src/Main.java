import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("PAGE REPLACEMENT ALGORITHMS SIMULATION");
            System.out.println("========================================");

            // 1. Get Inputs
            System.out.print("Enter a Page Reference string without space <Max 20>: ");
            String refString = scanner.next();

            System.out.print("Enter the size of Memory Frame <Max 10>: ");
            int frameSize = scanner.nextInt();

            boolean sameString = true;
            while (sameString) {
                printMenu();
                int choice = scanner.nextInt();

                if (choice == 4) {
                    running = false;
                    break;
                }

                runAlgorithm(choice, refString, frameSize);

                System.out.print("\nEnter Y to continue or press any other key to Exit: ");
                String cont = scanner.next();
                if (!cont.equalsIgnoreCase("Y")) {
                    running = false;
                    break;
                }

                System.out.print("Use the same reference string? (Y/N): ");
                String reuse = scanner.next();
                if (!reuse.equalsIgnoreCase("Y")) {
                    sameString = false;
                }
            }
        }
        System.out.println("Exiting simulation. Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nAlgorithm Option Menu:");
        System.out.println("====================");
        System.out.println("1. OPTIMAL");
        System.out.println("2. LRU");
        System.out.println("3. FIFO");
        System.out.println("4. Exit simulation");
        System.out.print("\nSelect an OPTION from above by entering its number: ");
    }

    private static void runAlgorithm(int choice, String refStr, int size) {
        // This will call your algorithm classes in the next step
        switch (choice) {
            case 1:
                System.out.println("\nOPTIMAL PAGE REPLACEMENT ALGORITHM");
                System.out.println("======================================");
            break;
            case 2:
                System.out.println("\nLRU PAGE REPLACEMENT ALGORITHM");
                System.out.println("=====================================");
                break;
            case 3:
                System.out.println("\nFIFO PAGE REPLACEMENT ALGORITHM");
                System.out.println("=====================================");
                break;
        }
        System.out.println("Simulation output for " + refStr + " will appear here...");
    }
}