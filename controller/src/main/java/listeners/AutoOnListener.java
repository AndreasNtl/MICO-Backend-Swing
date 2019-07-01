package listeners;

import autostate.AutomationSettings;
import gui.MainWindow;
import threads.AutomaticThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoOnListener implements ActionListener {
    private MainWindow mainWindow;

    public AutoOnListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getButtonMusicOn().setEnabled(false);
        mainWindow.getButtonMusicOff().setEnabled(false);
        mainWindow.getButtonFlashOn().setEnabled(false);
        mainWindow.getButtonFlashOff().setEnabled(false);
        mainWindow.getButtonAutoOn().setEnabled(false);
        mainWindow.getButtonAutoOff().setEnabled(true);
        mainWindow.getFlashDurationList().setEnabled(false);
        mainWindow.getMusicDurationList().setEnabled(false);
        mainWindow.getButtonPatternOn().setEnabled(false);
        mainWindow.getButtonPatternOff().setEnabled(false);
        mainWindow.getButtonAndroidOn().setEnabled(false);
        mainWindow.getButtonAndroidOff().setEnabled(false);
        mainWindow.getButtonStartClassification().setEnabled(false);
        mainWindow.getButtonStopClassification().setEnabled(false);
        mainWindow.appendToLog("Automatic mode enabled");

        AutomaticThread thread = new AutomaticThread(mainWindow);
        AutomationSettings.getInstance().setThread(thread);
        thread.start();
    }
}
