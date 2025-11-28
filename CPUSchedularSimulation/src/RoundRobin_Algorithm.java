import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Comparator;

public class RoundRobin_Algorithm {

    public static void runSimulation(ArrayList<Process> inputList, int timeQuantum) {
        System.out.println("\n======================================");
        System.out.println("   Round Robin CPU Scheduling (TQ=" + timeQuantum + ")");
        System.out.println("======================================");

        // 1. Create a copy of the list and sort by Arrival Time
        ArrayList<Process> processes = new ArrayList<>(inputList);
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        // 2. Setup tracking variables
        int currentTime = 0;
        Queue<Process> readyQueue = new LinkedList<>();
        ArrayList<Process> completedList = new ArrayList<>();

        // Map to track remaining burst time for each process (ID -> Remaining Time)
        Map<String, Integer> remainingBurstMap = new HashMap<>();
        for (Process p : processes) {
            remainingBurstMap.put(p.id, p.burstTime);
        }

        // Helper list to track which processes have already been added to the ready queue
        ArrayList<Process> addedToQueue = new ArrayList<>();

        // 3. StringBuilder for Gantt Chart
        StringBuilder ganttChart = new StringBuilder("|");

        // 4. Initial Queue Population
        // Add processes that have arrived at time 0
        for (Process p : processes) {
            if (p.arrivalTime <= currentTime) {
                readyQueue.add(p);
                addedToQueue.add(p);
            }
        }

        // 5. Main Simulation Loop
        while (!readyQueue.isEmpty() || addedToQueue.size() < processes.size()) {

            // Scenario: Queue is empty but processes are yet to arrive
            if (readyQueue.isEmpty()) {
                // Fast-forward time to the next arriving process
                int nextArrivalTime = Integer.MAX_VALUE;
                for (Process p : processes) {
                    if (!addedToQueue.contains(p)) {
                        nextArrivalTime = Math.min(nextArrivalTime, p.arrivalTime);
                    }
                }

                // If we found a valid next arrival time, update current time
                if (nextArrivalTime != Integer.MAX_VALUE) {
                    currentTime = nextArrivalTime;
                }

                // Add newly arrived processes
                for (Process p : processes) {
                    if (p.arrivalTime <= currentTime && !addedToQueue.contains(p)) {
                        readyQueue.add(p);
                        addedToQueue.add(p);
                    }
                }
            }

            // Even with the fast-forward logic, strictly check if queue is empty before polling
            if (readyQueue.isEmpty()) {
                continue;
            }

            // Get the process from the front of the queue
            Process currentProcess = readyQueue.poll();

            // Double check to satisfy static analysis tools
            if (currentProcess == null) continue;

            int remainingTime = remainingBurstMap.get(currentProcess.id);

            // Calculate actual execution time (slice)
            int timeSlice = Math.min(timeQuantum, remainingTime);

            // Execute Process
            ganttChart.append(" ")
                    .append(currentProcess.id)
                    .append("(")
                    .append(timeSlice) // Display Duration
                    .append(")|");

            currentTime += timeSlice;
            remainingBurstMap.put(currentProcess.id, remainingTime - timeSlice);

            // CRITICAL STEP: Check for new arrivals BEFORE re-queuing the current process
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && !addedToQueue.contains(p)) {
                    readyQueue.add(p);
                    addedToQueue.add(p);
                }
            }

            // If process is not finished, put it back at the tail of the queue
            if (remainingBurstMap.get(currentProcess.id) > 0) {
                readyQueue.add(currentProcess);
            } else {
                // Process is finished
                currentProcess.completionTime = currentTime;
                currentProcess.turnAroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnAroundTime - currentProcess.burstTime;
                completedList.add(currentProcess);
            }
        }

        // 6. Print Data Table
        System.out.printf("%-12s %-24s %-15s\n", "Process ID", "Arrival Time", "Burst Time");
        System.out.println("----------------------------------------------");
        for(Process p : inputList) {
            System.out.println(p);
        }

        // 7. Print Gantt Chart
        System.out.println("\nGantt Chart <with starting time zero>:");
        System.out.println(ganttChart);

        // 8. Print Individual Waiting Times & Average
        System.out.println("\nProcess Waiting Time:");
        double totalWaitingTime = 0;

        // Sorting completed list by ID for cleaner output
        completedList.sort(Comparator.comparing(p -> p.id));

        for (Process p : completedList) {
            System.out.printf("Waiting time of process, %s = %d ms.\n", p.id, p.waitingTime);
            totalWaitingTime += p.waitingTime;
        }

        if (!processes.isEmpty()) {
            double avgWaitingTime = totalWaitingTime / processes.size();
            System.out.printf("\nThe Average Process Waiting Time = %.2f ms.\n", avgWaitingTime);
        } else {
            System.out.println("\nThe Average Process Waiting Time = 0.00 ms.\n");
        }
    }
}