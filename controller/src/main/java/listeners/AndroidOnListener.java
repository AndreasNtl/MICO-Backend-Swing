package listeners;

import androidstate.AndroidSettings;
import gui.MainWindow;
import threads.AndroidThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AndroidOnListener implements ActionListener {
    private MainWindow mainWindow;

    public AndroidOnListener(MainWindow mainWindow) {
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
        mainWindow.getButtonAndroidOn().setEnabled(false);
        mainWindow.getButtonAndroidOff().setEnabled(true);
        mainWindow.getButtonPatternOn().setEnabled(false);
        mainWindow.getButtonPatternOff().setEnabled(false);

        mainWindow.getFlashDurationList().setEnabled(false);
        mainWindow.getMusicDurationList().setEnabled(false);
        mainWindow.appendToLog("Automatic mode enabled");

        AndroidThread thread = new AndroidThread(mainWindow);
        AndroidSettings.getInstance().setThread(thread);
        thread.start();
    }
}
