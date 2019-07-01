package listeners;

import gui.MainWindow;
import threads.PublisherThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashOffListener implements ActionListener {
    private MainWindow mainWindow;

    public FlashOffListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String message = "flashOff 0";

        PublisherThread thread = new PublisherThread(message);
        thread.start();
        mainWindow.appendToLog("Flash disabled");

    }
}
