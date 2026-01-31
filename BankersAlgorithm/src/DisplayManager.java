public class DisplayManager {

    public static void printSystemState(BankersState state) {
        System.out.println("\n--- Current System State ---");

        System.out.println("\nResources (Total System Capacity): ");
        printResourceVector(state.totalResourcesVector);

        System.out.println("\nMaximum Allocation Matrix:");
        printTable(state.maxMatrix, state.numProcesses, state.numResources);

        System.out.println("\nInstances Allocated Matrix:");
        printTable(state.allocationMatrix, state.numProcesses, state.numResources);

        System.out.println("\nNeed Matrix (Calculated):");
        printTable(state.needMatrix, state.numProcesses, state.numResources);

        System.out.println("\nAvailable Vector (Calculated):");
        printResourceVector(state.availableVector);

        System.out.println();
    }

    // Public for use in Iterations
    public static void printResourceVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print("R" + i + "=" + vector[i]);
            if (i < vector.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    // Public for use in Iterations
    public static void printTable(int[][] data, int rows, int cols) {
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