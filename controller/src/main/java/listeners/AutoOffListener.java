package listeners;

import autostate.AutomationSettings;
import gui.MainWindow;
import threads.AutomaticThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoOffListener  implements ActionListener {
    private MainWindow mainWindow;

    public AutoOffListener(MainWindow mainWindow) {
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
        mainWindow.getButtonPatternOn().setEnabled(true);
        mainWindow.getButtonStartClassification().setEnabled(true);
        mainWindow.getButtonStopClassification().setEnabled(false);
        mainWindow.appendToLog("Automatic mode disabled");

        synchronized(AutomationSettings.class) {
            AutomaticThread thread = AutomationSettings.getInstance().getThread();
            if (thread != null) {
                thread.interrupt();
                AutomationSettings.getInstance().setThread(null);
            }
        }

    }
}
