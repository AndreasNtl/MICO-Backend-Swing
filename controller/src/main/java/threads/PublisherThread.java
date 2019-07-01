package threads;

import mqtt.Publisher;

public class PublisherThread extends Thread {
    String message;

    public PublisherThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        super.run();

        Publisher p = new Publisher();
        p.Publish(message);
    }
}
