public class OptimalAlgorithm implements ReplacementAlgorithm {

    @Override
    public int findVictim(MemoryFrame memory, String[] pages, int currentPos) {
        int frameSize = memory.getSize();
        int farthestDistance = -1;
        int victimIndex = 0;

        // Iterate through all frames to assess each candidate page
        for (int f = 0; f < frameSize; f++) {
            String pageInFrame = memory.getPageAt(f);
            int nextUse = -1;

            // LOOK AHEAD: Scan future pages to find when this page is needed next
            for (int k = currentPos + 1; k < pages.length; k++) {
                if (pages[k].equals(pageInFrame)) {
                    nextUse = k;
                    break; // Found it, stop scanning
                }
            }

            // CRITICAL OPTIMAL LOGIC:
            // 1. If a page is never used again (nextUse == -1), it is the perfect victim.
            if (nextUse == -1) {
                return f; // Return immediately, we can't do better than this.
            }

            // 2. Otherwise, find the page used farthest in the future.
            if (nextUse > farthestDistance) {
                farthestDistance = nextUse;
                victimIndex = f;
            }
        }

        return victimIndex;
    }
}