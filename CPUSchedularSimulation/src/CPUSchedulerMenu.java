import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CPUSchedulerMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("       CPU SCHEDULING SIMULATOR       ");
        System.out.println("======================================");

        ArrayList<Process> processList = new ArrayList<>();
        boolean sessionActive = true;

        while (sessionActive) {
            // STEP 1: Collect Data (Only if the list is empty)
            if (processList.isEmpty()) {
                int numProcesses = getNumberOfProcesses();

                System.out.print("\nEnter 'r' to generate random Arrival and Burst times, or press Enter for manual input: ");
                String inputMode = scanner.nextLine().trim().toLowerCase();

                if (inputMode.equals("r")) {
                    generateRandomData(numProcesses, processList);
                } else {
                    getManualData(numProcesses, processList);
                }
            }

            // STEP 2: Display Data
            printProcessTable(processList);

            // STEP 3: Select and Run Algorithm
            runAlgorithmSelector(processList);

            // STEP 4: Session Loop Logic (Redo / New Data)
            System.out.print("\nDo you want to run another simulation? (y/n): ");
            String continueChoice = scanner.next().trim().toLowerCase();
            scanner.nextLine(); // Consume newline

            if (continueChoice.equals("y")) {
                System.out.print("Do you want to use the same process data? (y/n): ");
                String keepDataChoice = scanner.next().trim().toLowerCase();
                scanner.nextLine(); // Consume newline

                if (keepDataChoice.equals("n")) {
                    processList.clear(); // Clearing list forces Step 1 to run again
                    System.out.println("\n> Clearing data for new inputs...");
                } else {
                    System.out.println("\n> Retaining current process data...");
                }
            } else {
                sessionActive = false;
                System.out.println("Exiting simulator. Goodbye!");
            }
        }

        scanner.close();
    }


    // ================= HELPER METHODS =================

    private static int getNumberOfProcesses() {
        int num;
        while (true) {
            System.out.print("Input the number of processes <max 6>: ");
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num >= 1 && num <= 6) {
                    scanner.nextLine(); // Consume newline
                    break;
                } else {
                    System.out.println("Error: Please enter a number between 1 and 6.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return num;
    }

    private static void generateRandomData(int n, ArrayList<Process> list) {
        Random rand = new Random();
        System.out.println("\n> Generating random data...");
        for (int i = 1; i <= n; i++) {
            int arrival = rand.nextInt(11);      // 0 to 10
            int burst = rand.nextInt(20) + 1;    // 1 to 20
            list.add(new Process("P" + i, arrival, burst));
        }
    }

    private static void getManualData(int n, ArrayList<Process> list) {
        System.out.println("\n> Enter process details:");
        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Process P" + i + " ---");
            int arrival = -1;
            int burst = -1;

            while(arrival < 0) {
                System.out.print("  Arrival Time (>= 0): ");
                if(scanner.hasNextInt()) arrival = scanner.nextInt();
                else { System.out.println("Invalid input."); scanner.next(); }
            }

            while(burst <= 0) {
                System.out.print("  Burst Time (> 0):    ");
                if(scanner.hasNextInt()) burst = scanner.nextInt();
                else { System.out.println("Invalid input."); scanner.next(); }
            }
            list.add(new Process("P" + i, arrival, burst));
        }
        scanner.nextLine(); // Consume newline
    }

    private static void printProcessTable(ArrayList<Process> list) {
        System.out.println("\n==============================================");
        // Updated alignment to match Process.java (10, 18, 15)
        System.out.printf("%-10s %-18s %-15s\n", "Process ID", "Arrival Time", "Burst Time");
        System.out.println("----------------------------------------------");
        for (Process p : list) {
            System.out.println(p);
        }
        System.out.println("==============================================");
    }

    private static void runAlgorithmSelector(ArrayList<Process> processList) {
        System.out.println("\nCPU Scheduling Algorithms");
        System.out.println("=========================");
        System.out.println("1. FCFS (First Come First Served)");
        System.out.println("2. Round Robin");
        System.out.println("3. Exit System");
        System.out.print("\nSelect an algorithm by entering its choice number: ");

        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }
        scanner.nextLine(); // Consume newline after int input

        switch (choice) {
            case 1:
                FCFS_Algorithm.runSimulation(processList);
                break;
            case 2:
                System.out.print("\n[System] Enter Time Quantum for Round Robin: ");
                int timeQuantum = 0;
                if(scanner.hasNextInt()) timeQuantum = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                // We will link the Round Robin file here later
                // RoundRobin_Algorithm.runSimulation(processList, timeQuantum);
                System.out.println("[System] Running Round Robin (TQ=" + timeQuantum + ")... (Logic not yet linked)");
                break;
            case 3:
                System.out.println("Exiting program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }
}