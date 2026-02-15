public class FIFOAlgorithm implements ReplacementAlgorithm {

    private int victimPointer = 0; // Points to the oldest page (First In)

    @Override
    public int findVictim(MemoryFrame memory, String[] pages, int currentPos) {
        // 1. The victim is simply where the pointer is currently pointing.
        int victimIndex = victimPointer;

        // 2. Move the pointer to the next frame for the *next* page fault.
        //    We use modulo (%) to wrap around to 0 when we reach the end.
        victimPointer = (victimPointer + 1) % memory.getSize();

        // 3. Return the chosen victim index
        return victimIndex;
    }
}