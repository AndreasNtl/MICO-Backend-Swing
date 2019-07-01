package queue;

import java.util.LinkedList;
import java.util.List;

public class Queue {
    public final static int EYES_OPEN = 1;
    public final static int EYES_CLOSED = 2;

    public List<Integer> buffer = new LinkedList<>();

    public synchronized int get() {
        try {
            while (buffer.isEmpty()) {
                wait();
            }
            int value = buffer.remove(0).intValue();
            return value;
        } catch (InterruptedException e) {
            // e.printStackTrace();
            return -1;
        }
    }

    public synchronized void put(int value) {
        buffer.add(value);
    }
}

