package listeners;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassificationOnListener implements ActionListener {
    private MainWindow mainWindow;

    public ClassificationOnListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getButtonMusicOn().setEnabled(false);
        mainWindow.getButtonMusicOff().setEnabled(false);
        mainWindow.getButtonFlashOn().setEnabled(false);
        mainWindow.getButtonFlashOff().setEnabled(false);
        mainWindow.getButtonAutoOn().setEnabled(false);
        mainWindow.getButtonAutoOff().setEnabled(false);
        mainWindow.getFlashDurationList().setEnabled(false);
        mainWindow.getMusicDurationList().setEnabled(false);
        mainWindow.getButtonPatternOn().setEnabled(false);
        mainWindow.getButtonAndroidOn().setEnabled(false);
        mainWindow.getButtonAndroidOff().setEnabled(false);
        mainWindow.getButtonStartClassification().setEnabled(false);
        mainWindow.getButtonStopClassification().setEnabled(true);
        mainWindow.getButtonPatternOff().setEnabled(false);
        mainWindow.appendToLog("Classification enabled");

        // ---------------------------_

        mainWindow.getProducerThread().start();
        mainWindow.getConsumerThread().start();
    }
}
