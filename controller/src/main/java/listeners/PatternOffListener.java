package listeners;

import autostate.AutomationSettings;
import gui.MainWindow;
import threads.AutomaticThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatternOffListener implements ActionListener {
    private MainWindow mainWindow;

    public PatternOffListener(MainWindow mainWindow) {
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
        mainWindow.getButtonPatternOff().setEnabled(false);
        mainWindow.getButtonPatternOn().setEnabled(true);
        mainWindow.getButtonStartClassification().setEnabled(true);
        mainWindow.getButtonStopClassification().setEnabled(false);
        mainWindow.appendToLog("Pattern mode disabled");

        if (PatternOnListener.thread != null) {
            PatternOnListener.thread.interrupt();
            PatternOnListener.thread = null;
        }
        synchronized(AutomationSettings.class) {
            AutomaticThread thread = AutomationSettings.getInstance().getThread();
            if (thread != null) {
                thread.interrupt();
                AutomationSettings.getInstance().setThread(null);
            }
        }

    }
}
