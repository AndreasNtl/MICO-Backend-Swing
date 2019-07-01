package listeners;

import autostate.AutomationSettings;
import gui.MainWindow;
import threads.AutomaticThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassificationOffListener implements ActionListener {
    private MainWindow mainWindow;

    public ClassificationOffListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getButtonMusicOn().setEnabled(true);
        mainWindow.getButtonMusicOff().setEnabled(true);
        mainWindow.getButtonFlashOn().setEnabled(true);
        mainWindow.getButtonFlashOff().setEnabled(true);
        mainWindow.getButtonAutoOn().setEnabled(true);
        mainWindow.getButtonAutoOff().setEnabled(false);
        mainWindow.getFlashDurationList().setEnabled(true);
        mainWindow.getMusicDurationList().setEnabled(true);
        mainWindow.getButtonAndroidOn().setEnabled(true);
        mainWindow.getButtonAndroidOff().setEnabled(false);
        mainWindow.getButtonStartClassification().setEnabled(true);
        mainWindow.getButtonStopClassification().setEnabled(false);
        mainWindow.getButtonPatternOn().setEnabled(true);
        mainWindow.getButtonPatternOff().setEnabled(false);
        mainWindow.appendToLog("Classification disabled");

        synchronized(AutomationSettings.class) {
            AutomaticThread thread = AutomationSettings.getInstance().getThread();
            if (thread != null) {
                thread.interrupt();
                AutomationSettings.getInstance().setThread(null);
            }
        }



        // ---------------------------_
        mainWindow.getProducerThread().interrupt();
        mainWindow.getConsumerThread().interrupt();
    }
}
