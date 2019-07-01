package listeners;

import gui.MainWindow;
import threads.PublisherThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicOnListener  implements ActionListener {
    private MainWindow mainWindow;

    public MusicOnListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int duration = mainWindow.getMusicDurationList().getSelectedIndex() + 1;

        duration = duration * 1000;

        mainWindow.appendToLog("Music on for " + duration/1000 + " seconds ");

        String message = "musicOn " + duration;

        PublisherThread thread = new PublisherThread(message);
        thread.start();
    }
}
