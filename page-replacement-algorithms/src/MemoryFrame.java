import java.util.Arrays;

public class MemoryFrame {
    private final String[] frames;
    private final int size;

    public MemoryFrame(int size) {
        this.size = size;
        this.frames = new String[size];
        Arrays.fill(frames, " ");
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + "[ " + frames[i] + " ]");
        }
    }

    public boolean contains(String page) {
        for (String f : frames) {
            if (f != null && f.equals(page)) return true;
        }
        return false;
    }

    public void updateFrame(int index, String page) {
        frames[index] = page;
    }

    public boolean isFull() {
        for (String f : frames) {
            if (f.equals(" ")) return false;
        }
        return true;
    }

    public int getFirstEmptyIndex() {
        for (int i = 0; i < size; i++) {
            if (frames[i].equals(" ")) return i;
        }
        return -1;
    }

    public String getPageAt(int index) {
        if (index >= 0 && index < size) {
            return frames[index];
        }
        return " ";
    }

    // --- NEW METHOD ADDED HERE ---
    public int getSize() {
        return size;
    }
}