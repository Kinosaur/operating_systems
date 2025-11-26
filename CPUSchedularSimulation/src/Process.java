public class Process {
    String id;
    int arrivalTime;
    int burstTime;

    // We will add these to store results later
    int startTime;
    int completionTime;
    int waitingTime;
    int turnAroundTime;

    public Process(String id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-18d %-15d", id, arrivalTime, burstTime);
    }
}