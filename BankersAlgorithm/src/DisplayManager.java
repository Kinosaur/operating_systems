public class DisplayManager {

    public static void printSystemState(BankersState state) {
        System.out.println("\n--- Current System State ---");

        System.out.println("\nResources (Total System Capacity): ");
        printVector(state.totalResourcesVector, "R");

        System.out.println("\nAvailable Vector (Currently Free):");
        printVector(state.availableVector, "R");

        System.out.println("\nMaximum Allocation Matrix:");
        printTable(state.maxMatrix, state.numProcesses, state.numResources);

        System.out.println("\nInstances Allocated Matrix:");
        printTable(state.allocationMatrix, state.numProcesses, state.numResources);

        System.out.println("\nNeed Matrix (Calculated):");
        printTable(state.needMatrix, state.numProcesses, state.numResources);

        System.out.println();
    }

    private static void printVector(int[] vector, String label) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print(label + i + "=" + vector[i]);
            if (i < vector.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    private static void printTable(int[][] data, int rows, int cols) {
        // Print Column Headers
        System.out.print("      ");
        for (int j = 0; j < cols; j++) {
            System.out.printf("R%-3d ", j);
        }
        System.out.println();

        // Print Rows
        for (int i = 0; i < rows; i++) {
            System.out.printf("P%-4d ", i);
            for (int j = 0; j < cols; j++) {
                System.out.printf("%-4d ", data[i][j]);
            }
            System.out.println();
        }
    }
}