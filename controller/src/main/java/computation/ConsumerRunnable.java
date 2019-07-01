package computation;

import autostate.AutomationSettings;
import queue.Queue;
import settings.AdministratorSettings;
import threads.PublisherThread;

//Consumer Class in Java
public class ConsumerRunnable implements Runnable {

    private final Queue sharedQueue;

    public ConsumerRunnable(Queue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int t;

                synchronized (AutomationSettings.class) {
                    t = AdministratorSettings.getInstance().getAndroid_frequency();
                }

                if (t == 0) {
                    t = 1000;
                }

                Thread.sleep(t);

                int value = sharedQueue.get();

                System.out.println("Consumed value " + value + " " + ((value == Queue.EYES_OPEN) ? "EYES_OPEN" : "EYES_CLOSED"));

                switch (value) {
                    case Queue.EYES_CLOSED: {
                        PublisherThread thread = new PublisherThread("EYES_CLOSED 0");
                        thread.start();
                        break;
                    }
                    case Queue.EYES_OPEN: {
                        PublisherThread thread = new PublisherThread("EYES_OPEN 0");
                        thread.start();
                        break;
                    }
                }

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

            }
        } catch (InterruptedException ex) {
            System.out.println("Consumer terminated");
            return;
        }
    }

}

