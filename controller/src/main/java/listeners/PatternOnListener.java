package listeners;

import gui.MainWindow;
import settings.AdministratorSettings;
import threads.PatternThread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatternOnListener implements ActionListener {
    private MainWindow mainWindow;
    private AdministratorSettings settings;

    public static PatternThread  thread = null;

    public  PatternOnListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        settings = AdministratorSettings.getInstance();
        mainWindow.getButtonMusicOn().setEnabled(false);
        mainWindow.getButtonMusicOff().setEnabled(false);
        mainWindow.getButtonFlashOn().setEnabled(false);
        mainWindow.getButtonFlashOff().setEnabled(false);
        mainWindow.getButtonAutoOn().setEnabled(false);
        mainWindow.getButtonPatternOff().setEnabled(true);
        mainWindow.getFlashDurationList().setEnabled(false);
        mainWindow.getMusicDurationList().setEnabled(false);
        mainWindow.getButtonPatternOn().setEnabled(false);
        mainWindow.getButtonAutoOn().setEnabled(false);
        mainWindow.getButtonAndroidOn().setEnabled(false);
        mainWindow.getButtonAndroidOff().setEnabled(false);
        mainWindow.getButtonStartClassification().setEnabled(false);
        mainWindow.getButtonStopClassification().setEnabled(false);
        mainWindow.appendToLog(settings.getPattern().toUpperCase() + " pattern for 1 time");

        String [] msg = new String[] { settings.getPattern() } ;
        thread = new PatternThread(msg);
        thread.start();
    }
}
