package listeners;

import gui.MainWindow;
import threads.PublisherThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashOnListener implements ActionListener {
    private MainWindow mainWindow;

    public FlashOnListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int duration = mainWindow.getFlashDurationList().getSelectedIndex() + 1;

        duration = duration * 1000;

        mainWindow.appendToLog("Flash on for " + duration/1000 + " seconds ");

        String message = "flashOn " + duration;

        PublisherThread thread = new PublisherThread(message);
        thread.start();
    }
}
