import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class InputManager {

    public static boolean loadFromFile(String filename, BankersState state) {
        File file = new File(filename);

        try (Scanner fileScanner = new Scanner(file)) {
            // Block 1: Read Max Matrix
            for (int i = 0; i < state.numProcesses; i++) {
                for (int j = 0; j < state.numResources; j++) {
                    if (fileScanner.hasNextInt())
                        state.maxMatrix[i][j] = fileScanner.nextInt();
                }
            }

            // Block 2: Read Allocation Matrix
            for (int i = 0; i < state.numProcesses; i++) {
                for (int j = 0; j < state.numResources; j++) {
                    if (fileScanner.hasNextInt())
                        state.allocationMatrix[i][j] = fileScanner.nextInt();
                }
            }

            // Block 3: Read Total Resources Vector
            for (int j = 0; j < state.numResources; j++) {
                if (fileScanner.hasNextInt())
                    state.totalResourcesVector[j] = fileScanner.nextInt();
            }

            System.out.println(">> Data loaded successfully.");
            return true;

        } catch (FileNotFoundException e) {
            System.err.println("\n[ERROR] File not found: " + filename);
            return false;
        }
    }

    public static void generateRandomData(BankersState state) {
        Random rand = new Random();
        System.out.println("\n>> Generating random simulation data...");

        // Generate Total Resources
        for (int j = 0; j < state.numResources; j++) {
            state.totalResourcesVector[j] = rand.nextInt(10) + 10;
        }

        for (int i = 0; i < state.numProcesses; i++) {
            for (int j = 0; j < state.numResources; j++) {
                // Max demand <= Total Resources
                state.maxMatrix[i][j] = rand.nextInt(state.totalResourcesVector[j] / 2);
                // Allocation <= Max
                state.allocationMatrix[i][j] = rand.nextInt(state.maxMatrix[i][j] + 1);
            }
        }
    }
}