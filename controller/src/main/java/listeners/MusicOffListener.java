package listeners;

import gui.MainWindow;
import threads.PublisherThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicOffListener implements ActionListener {
    private MainWindow mainWindow;

    public MusicOffListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String message = "musicOff 0";

        PublisherThread thread = new PublisherThread(message);
        thread.start();
        mainWindow.appendToLog("Music disabled");

    }
}
