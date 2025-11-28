# CPU Scheduling Simulator (Java)

A console-based CPU Scheduling Simulator built in Java. This application simulates two fundamental Operating System scheduling algorithms: **First-Come, First-Served (FCFS)** and **Round Robin (RR)**.

It allows users to generate process data randomly or enter it manually, visualize the execution via a text-based Gantt Chart, and calculate average waiting times.

---

## 🚀 Features

### Two Scheduling Algorithms:
- **FCFS (First-Come, First-Served):** Non-preemptive scheduling based on arrival time.
- **Round Robin:** Preemptive scheduling with a user-defined Time Quantum (TQ).

### Flexible Data Input:
- **Manual Input:** Enter Arrival Time and Burst Time for up to 6 processes.
- **Random Generation:** Automatically generates randomized data for quick testing.

### Visual Gantt Charts:
- Displays execution flow in a text-based format.
- **FCFS Style:** Uses inclusive time indexing (e.g., `0-5` for a 6ms burst starting at 0).
- **Round Robin Style:** Displays execution duration per slice (e.g., `P1(4)`).

### Session Management:
- Run multiple simulations in one session.
- Option to reuse the same dataset to compare FCFS vs. Round Robin performance on identical processes.

---

## 📂 Project Structure

| File | Description |
|------|-------------|
| `CPUSchedulerMenu.java` | Main Entry Point. Handles user input, data generation, and the main application loop. |
| `Process.java` | Defines the Process object (ID, Arrival Time, Burst Time) and formatting logic. |
| `FCFS_Algorithm.java` | Contains the logic and visualization for the First-Come, First-Served algorithm. |
| `RoundRobin_Algorithm.java` | Contains the logic, queue management, and visualization for the Round Robin algorithm. |

---

## 🛠️ How to Run

1. **Prerequisites:** Ensure you have Java installed (JDK 8 or higher).

2. **Compile:** Open your terminal/command prompt in the project directory and run:
   ```bash
   javac *.java
   ```

3. **Execute:** Run the main menu class:
   ```bash
   java CPUSchedulerMenu
   ```

---

## 📊 Algorithm Details

### 1. First-Come, First-Served (FCFS)
- **Type:** Non-Preemptive.
- **Logic:** Processes are sorted strictly by Arrival Time. The CPU executes processes in the order they arrive.
- **Gantt Chart Format:** `| P1(Start-End) |`
- **Note:** The chart uses inclusive indexing for the end time (e.g., a process running from 0 to 6 is shown as `0-5`).

### 2. Round Robin (RR)
- **Type:** Preemptive.
- **Logic:** Processes are executed in a cyclic queue. Each process runs for a small unit of time called a Time Quantum (TQ). If a process isn't finished within the TQ, it is moved to the back of the queue.
- **Queue Logic:** Strictly follows `Execute -> Check New Arrivals -> Re-queue Current Process`.
- **Gantt Chart Format:** `| P1(Duration) |` (e.g., `P1(4)` means P1 ran for 4ms in that specific slice).

---

## 📝 Example Usage

```
======================================
       CPU SCHEDULING SIMULATOR       
======================================
Input the number of processes <max 6>: 3

Enter 'r' to generate random Arrival and Burst times, or press Enter for manual input: r

> Generating random data...

==============================================
Process ID   Arrival Time             Burst Time     
----------------------------------------------
P1           0                        5              
P2           2                        8              
P3           4                        3              
==============================================

CPU Scheduling Algorithms
=========================
1. FCFS (First Come First Served)
2. Round Robin
3. Exit System

Select an algorithm by entering its choice number: 2
[System] Enter Time Quantum for Round Robin: 2

======================================
   Round Robin CPU Scheduling (TQ=2)
======================================

Gantt Chart <with starting time zero>:
| P1(2)| P2(2)| P1(2)| P3(2)| P2(2)| P1(1)| P3(1)| P2(2)| P2(2)|

Process Waiting Time:
Waiting time of process, P1 = 9 ms.
Waiting time of process, P2 = 8 ms.
Waiting time of process, P3 = 8 ms.

The Average Process Waiting Time = 8.33 ms.
```

---

## 👤 Author

[Kinosaur](https://github.com/Kinosaur)
