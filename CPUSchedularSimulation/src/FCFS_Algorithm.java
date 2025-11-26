import java.util.ArrayList;
import java.util.Comparator;

public class FCFS_Algorithm {

    public static void runSimulation(ArrayList<Process> inputList) {
        System.out.println("\n======================================");
        System.out.println("      FCFS CPU Scheduling Algorithm     ");
        System.out.println("======================================");

        // 1. Create a copy of the list to avoid messing up the original order
        ArrayList<Process> processes = new ArrayList<>(inputList);

        // 2. Sort processes by Arrival Time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        double totalWaitingTime = 0;

        // StringBuilder to construct the Gantt Chart string
        StringBuilder ganttChart = new StringBuilder("|");

        System.out.printf("%-10s %-18s %-15s\n", "Process ID", "Arrival Time", "Burst Time");
        System.out.println("----------------------------------------------");
        for(Process p : processes) {
            System.out.println(p);
        }

        // 3. Calculation Loop
        for (Process p : processes) {
            // Check for Idle Time
            if (currentTime < p.arrivalTime) {
                // If there is a gap, you could add an idle block here
                currentTime = p.arrivalTime;
            }

            p.startTime = currentTime;
            p.completionTime = p.startTime + p.burstTime;
            p.turnAroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.startTime - p.arrivalTime;

            // --- GANTT CHART GENERATION ---
            // OPTION 1: Inclusive Indexing (Matches your slide IMG_5988)
            // Example: Start 0, Burst 6 -> Shows (0-5)
            // Logic: (Start) - (End - 1)
            ganttChart.append(" ")
                    .append(p.id)
                    .append("(")
                    .append(p.startTime)
                    .append("-")
                    .append(p.completionTime - 1) // Subtract 1 for inclusive display
                    .append(")|");

            /* // OPTION 2: Simple Burst Display (Matches hand-drawing style)
            // Example: P1(6) where 6 is the burst time
            ganttChart.append(" ")
                      .append(p.id)
                      .append("(")
                      .append(p.burstTime)
                      .append(")|");
            */

            // Update global time
            currentTime = p.completionTime;

            // Add to total for average calculation
            totalWaitingTime += p.waitingTime;
        }

        // 4. Print Gantt Chart
        System.out.println("\nGantt Chart <with starting time zero>:");
        System.out.println(ganttChart);

        // 5. Print Individual Waiting Times
        System.out.println();
        for (Process p : processes) {
            System.out.printf("Waiting time of process, %s = %d ms.\n", p.id, p.waitingTime);
        }

        // 6. Print Average Waiting Time
        double avgWaitingTime = totalWaitingTime / processes.size();
        System.out.printf("\nThe Average Process Waiting Time = %.2f ms.\n", avgWaitingTime);
    }
}