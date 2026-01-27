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

        // Initialize arrays
        maxMatrix = new int[n][m];
        allocationMatrix = new int[n][m];
        needMatrix = new int[n][m];
        availableVector = new int[m];
        totalResourcesVector = new int[m];
    }

    // --- Core Logic ---

    public void calculateNeed() {
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                needMatrix[i][j] = maxMatrix[i][j] - allocationMatrix[i][j];
            }
        }
    }

    public void calculateAvailable() {
        int[] totalAllocated = new int[numResources];

        // 1. Sum up currently allocated resources
        for (int j = 0; j < numResources; j++) {
            for (int i = 0; i < numProcesses; i++) {
                totalAllocated[j] += allocationMatrix[i][j];
            }
        }

        // 2. Available = Total System Resources - Total Allocated
        for (int j = 0; j < numResources; j++) {
            availableVector[j] = totalResourcesVector[j] - totalAllocated[j];
        }
    }
}