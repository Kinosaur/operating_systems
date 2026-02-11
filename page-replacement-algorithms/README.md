# Page Replacement Algorithms Simulation

A Java-based simulation of Operating System **Virtual Memory Page Replacement Algorithms**.  
This program demonstrates **Demand Paging** by visualizing how logical pages are loaded into physical memory frames and how **page faults** occur under different replacement policies.

---

## 📖 Overview

Virtual Memory allows processes to execute even if they are not completely loaded into physical memory.  
When a program references a page that is not currently in memory, a **page fault** occurs, and the Operating System must decide which existing page should be replaced.

This simulator is designed to study and compare the behavior of classic page replacement algorithms through a step-by-step execution model.

The following algorithms are supported or planned:

1. **Optimal Page Replacement** (In Progress)  
   Replaces the page that will not be used for the longest period of time in the future.  
   This algorithm provides the theoretical minimum number of page faults.

2. **LRU (Least Recently Used)** (Planned)  
   Replaces the page that has not been used for the longest time in the past.

3. **FIFO (First-In-First-Out)** (Planned)  
   Replaces the page that has been in memory the longest.

---

## 🚀 Features

- **Step-by-Step Visualization**  
  Displays the state of each memory frame after every page reference using the format:  
  `index[ page ]`

- **Page Fault Tracking**  
  Clearly identifies page faults and shows which page is replaced.

- **Configurable Inputs**
  - Page reference string (maximum 20 characters)
  - Memory frame size (maximum 10 frames)

- **Object-Oriented Design**
  - Clear separation between user interface, simulation engine, memory model, and algorithm logic
  - Architecture designed for easy extension with new replacement algorithms

---

## 📂 Project Structure

| File | Description |
|------|------------|
| **`Main.java`** | Handles user input, menu display, and overall program flow. |
| **`SimulationEngine.java`** | Controls the simulation loop, detects page faults, and delegates victim selection to the selected algorithm. |
| **`MemoryFrame.java`** | Represents physical memory frames and displays their state. |
| **(Planned) Algorithm Classes** | Each replacement policy will be implemented as a separate module. |

---

## 🛠️ How to Run

### Prerequisites
- Java Development Kit (JDK) installed

### Compilation

Open a terminal in the project directory and compile:

```bash
javac *.java
````

### Execution

Run the program:

```bash
java Main
```

---

## 🖥️ Usage Example

### 1. Input Phase

```text
PAGE REPLACEMENT ALGORITHMS SIMULATION
========================================
Enter a Page Reference string without space <Max 20>: 232152453252
Enter the size of Memory Frame <Max 10>: 3
```

### 2. Simulation Phase

```text
Reference string: 2
0[ 2 ]
1[   ]
2[   ]

Reference string: 5
=> Page Fault!
Replace 1 with 5
0[ 2 ]
1[ 5 ]
2[ 3 ]
```

### 3. Result Phase

```text
Total Page Fault(s) generated: 8
```

*(Page fault count depends on the selected algorithm.)*

---

## 🧠 Theory Background

* **Demand Paging**
  Pages are loaded into memory only when they are referenced during execution.

* **Optimal Page Replacement**
  Achieves the lowest possible page-fault rate but requires knowledge of future memory references, making it impractical for real operating systems.

* **Victim Frame**
  The memory frame selected by the replacement algorithm to be evicted when a page fault occurs and memory is full.

---

## 📚 Reference

Based on concepts from
**Operating System Concepts (10th Edition)**
by Silberschatz, Galvin, and Gagne
