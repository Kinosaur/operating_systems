# Banker's Algorithm – Deadlock Avoidance Simulation

This project is a small Java application created for an **Operating Systems course (Deadlock lecture)**. It demonstrates how **Banker’s Algorithm** works for detecting safe and unsafe states in a system with multiple processes and resource types.

The goal of the project is educational: to make the algorithm visible, traceable, and understandable rather than optimized or over‑engineered.

---

## 📌 What This Project Does

- Simulates a system with **n processes** and **m resource types**
- Reads system state from an input file (or user input)
- Computes:
  - Allocation matrix
  - Maximum demand matrix
  - Need matrix
  - Available resources
- Applies **Banker’s Algorithm** to:
  - Check whether the system is in a **safe state**
  - Determine a **safe execution sequence**, if one exists

This directly maps to the deadlock avoidance concepts taught in OS theory.

---

## 🧠 Core Concept Refresher (Very Brief)

Banker’s Algorithm avoids deadlock by **only granting resource requests that keep the system in a safe state**. A state is safe if there exists *some order* in which all processes can complete without running out of resources.

This project focuses on **state validation**, not dynamic request handling.

---

## 🗂️ Project Structure

```
.
├── BankersAlgorithmApp.java   # Main entry point
├── BankersState.java          # Holds system matrices and vectors
├── InputManager.java          # Handles file and input parsing
├── DisplayManager.java        # Prints matrices and results
├── input1.txt                 # Sample input (safe state)
├── input2.txt                 # Sample input (may be unsafe)
└── README.md
```

---

## 🧩 File Responsibilities

### `BankersAlgorithmApp.java`
- Program entry point
- Handles user interaction
- Initializes system size
- Triggers loading, computation, and safety checking

### `BankersState.java`
- Stores all system data:
  - Max matrix
  - Allocation matrix
  - Need matrix
  - Total and available resources
- Computes derived values (e.g., Need = Max − Allocation)
- Runs the **safety algorithm**

### `InputManager.java`
- Loads matrices and vectors from input files
- Ensures values are read in correct order and format

### `DisplayManager.java`
- Prints matrices and vectors in a readable table format
- Displays safety results and execution sequences

---

## 📄 Input File Format

Each input file follows this structure:

1. **Max Matrix** (n rows × m columns)
2. **Allocation Matrix** (n rows × m columns)
3. **Total Resources Vector** (1 row × m values)

Example:

```
5 6 1 7
3 2 3 4
...

3 0 1 3
2 2 0 0
...

13 11 6 8
```

Values are space‑separated integers.

---

## ▶️ How to Run

1. Compile all Java files:

```bash
javac *.java
```

2. Run the program:

```bash
java BankersAlgorithmApp
```

3. Ensure the input file (e.g., `input1.txt`) is in the same directory.

---

## 🎯 Learning Objectives

This assignment helps reinforce:

- Deadlock avoidance vs deadlock detection
- Matrix‑based representation of resource allocation
- Safe vs unsafe system states
- Translating OS theory into working code

---

## ⚠️ Academic Integrity Note

This project is meant for **learning**, not copying.

If you are a student:
- **Do not submit this code as your own**
- Read it
- Trace it
- Rewrite it yourself

Understanding the algorithm matters far more than passing a compiler check.

---

## 📚 References

- Silberschatz, Galvin, Gagne – *Operating System Concepts*
- OS lecture notes on Deadlock and Banker’s Algorithm

---

*Built for clarity, not cleverness. The algorithm is the star — the code just holds the flashlight.*

