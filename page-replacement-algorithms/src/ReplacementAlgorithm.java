public interface ReplacementAlgorithm {
    /**
     * Determines which frame index should be replaced.
     * @param memory The current state of physical memory (to see what's loaded)
     * @param pages The full reference string (for looking ahead/behind)
     * @param currentPos The current index in the reference string
     * @return The index of the frame to be replaced (the victim)
     */
    int findVictim(MemoryFrame memory, String[] pages, int currentPos);
}