public class LRUAlgorithm implements ReplacementAlgorithm {

    @Override
    public int findVictim(MemoryFrame memory, String[] pages, int currentPos) {
        int frameSize = memory.getSize();
        int lruIndex = -1;       // The index in 'pages' that is furthest back
        int victimFrame = 0;     // The frame we want to replace

        // We want to find the MINIMUM last-used index.
        // Initialize 'leastRecentlyUsedValue' to a number larger than any possible index (like Infinity)
        int leastRecentlyUsedValue = Integer.MAX_VALUE;

        // Iterate through every frame to check its history
        for (int f = 0; f < frameSize; f++) {
            String pageInFrame = memory.getPageAt(f);

            // Scan backwards from current position to find the last usage
            boolean found = false;
            for (int k = currentPos - 1; k >= 0; k--) {
                if (pages[k].equals(pageInFrame)) {
                    // We found the most recent time this page was used
                    if (k < leastRecentlyUsedValue) {
                        leastRecentlyUsedValue = k;
                        victimFrame = f;
                    }
                    found = true;
                    break; // Stop scanning for this specific page, we found its last use
                }
            }

            // Safety check: If a page in memory was somehow never in the history
            // (e.g. preloaded), it is the ultimate LRU victim.
            if (!found) {
                return f;
            }
        }

        return victimFrame;
    }
}