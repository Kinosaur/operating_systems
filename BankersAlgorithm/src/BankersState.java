import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankersState {
    int numProcesses; // n
    int numResources; // m

    // Matrices: [Process][Resource]
    int[][] maxMatrix;
    int[][] allocationMatrix;
    int[][] needMatrix;

    // Vectors: [Resource]
    int[] totalResourcesVector;
    int[] availableVector;

    public BankersState(int n, int m) {
        this.numProcesses = n;
        this.numResources = m;

        maxMatrix = new int[n][m];
        allocationMatrix = new int[n][m];
        needMatrix = new int[n][m];
        availableVector = new int[m];
        totalResourcesVector = new int[m];
    }

    // --- Core Calculations ---

    public void calculateNeed() {
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                needMatrix[i][j] = maxMatrix[i][j] - allocationMatrix[i][j];
            }
        }
    }

    public void calculateAvailable() {
        int[] totalAllocated = new int[numResources];

        // Sum currently allocated resources
        for (int j = 0; j < numResources; j++) {
            for (int i = 0; i < numProcesses; i++) {
                totalAllocated[j] += allocationMatrix[i][j];
            }
        }

        // Available = Total System Resources - Total Allocated
        for (int j = 0; j < numResources; j++) {
            availableVector[j] = totalResourcesVector[j] - totalAllocated[j];
        }
    }

    // --- Safety Algorithm (Iteration Logic) ---

    public void runSafetyAlgorithm() {
        System.out.println("--------------------------------------------------");
        System.out.println("       Safety Algorithm Iteration Start           ");
        System.out.println("--------------------------------------------------");

        int[] work = Arrays.copyOf(availableVector, numResources);
        boolean[] finish = new boolean[numProcesses];
        List<Integer> safeSequence = new ArrayList<>();
        int completedCount = 0;

        while (completedCount < numProcesses) {

            // 1. Prepare Display Data (Zeroing out finished rows)
            int[][] displayNeed = new int[numProcesses][numResources];
            int[][] displayAllocation = new int[numProcesses][numResources];

            for (int i = 0; i < numProcesses; i++) {
                if (finish[i]) {
                    // If process is done, show 0s for visual clarity
                    Arrays.fill(displayNeed[i], 0);
                    Arrays.fill(displayAllocation[i], 0);
                } else {
                    // Otherwise, show actual values
                    displayNeed[i] = Arrays.copyOf(needMatrix[i], numResources);
                    displayAllocation[i] = Arrays.copyOf(allocationMatrix[i], numResources);
                }
            }

            // 2. Print System State for this Iteration
            System.out.println("\n[ Iteration " + (completedCount + 1) + " ]");

            System.out.println("Current Available Vector (Work):");
            DisplayManager.printResourceVector(work);

            System.out.println("(Completed processes shown as 0)\n");

            System.out.println("Current Allocation Matrix:");
            DisplayManager.printTable(displayAllocation, numProcesses, numResources);

            System.out.println("\n");

            System.out.println("Current Need Matrix:");
            DisplayManager.printTable(displayNeed, numProcesses, numResources);

            // 3. Find a candidate process
            boolean foundInThisPass = false;

            // System.out.println(">> Scanning for executable process...");

            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i]) {
                    // Check logic
                    if (checkNeedLessThanWork(i, work)) {
                        // --- MATCH FOUND BLOCK ---
                        System.out.println("   -> MATCH FOUND: Process P" + i);
                        System.out.println("      Need " + vectorToString(needMatrix[i]) + " <= Work " + vectorToString(work));

                        // Execute Process
                        // System.out.println("      P" + i + " runs, finishes, and releases resources.");
                        for (int k = 0; k < numResources; k++) {
                            work[k] += allocationMatrix[i][k];
                        }

                        finish[i] = true;
                        safeSequence.add(i);
                        completedCount++;
                        foundInThisPass = true;

                        System.out.println("      New Available Vector: " + vectorToString(work));

                        // Break to restart scan from P0 (First-Fit Strategy)
                        break;
                    }
                }
            }

            if (!foundInThisPass) {
                System.out.println("\n>> No matching process found in this iteration. Deadlock detected.");
                break;
            }
        }

        // --- Final Result ---
        System.out.println("\n");
        if (completedCount == numProcesses) {
            System.out.println("The resource allocation has been completed within " + completedCount + " iterations!");
            System.out.print("The Safe-state order: < ");
            for (int i = 0; i < safeSequence.size(); i++) {
                System.out.print("P" + safeSequence.get(i) + (i < safeSequence.size()-1 ? ", " : " "));
            }
            System.out.println(">");
        } else {
            System.out.println("Resource allocation is failed after " + completedCount + " iterations!");
            System.out.println("System is in an UNSAFE state.");
        }
    }

    private boolean checkNeedLessThanWork(int pIndex, int[] work) {
        for (int j = 0; j < numResources; j++) {
            if (needMatrix[pIndex][j] > work[j]) {
                return false;
            }
        }
        return true;
    }

    private String vectorToString(int[] vec) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vec.length; i++) {
            sb.append(vec[i]).append(i < vec.length - 1 ? ", " : "");
        }
        sb.append("]");
        return sb.toString();
    }
}