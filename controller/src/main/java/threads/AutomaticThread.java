package threads;

import autostate.AutomationSettings;
import gui.MainWindow;
import settings.AdministratorSettings;

import javax.swing.*;
import java.util.Random;

public class AutomaticThread extends Thread {
    private MainWindow mainWindow;

    public AutomaticThread(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run() {
        super.run();

        int maxt = AdministratorSettings.getInstance().getMax_delay();

//        int i =0;
        do {
            long t = new Random().nextInt(maxt);
            try {


                Thread.sleep(t);

                switch (new Random().nextInt(4)) {
                    case 0: {
                        int duration = 1+ (new Random().nextInt(10));
                        duration = duration * 1000;
                        String message = "musicOn " + duration;

                        PublisherThread thread = new PublisherThread(message);
                        thread.start();
                        break;
                    }
                    case 1: {
                        int duration = 1 + (new Random().nextInt(10));
                        duration = duration * 1000;
                        String message = "flashOn " + duration;

                        PublisherThread thread = new PublisherThread(message);
                        thread.start();
                        break;
                    }
                    case 2: {
                        String message = "flashOff 0";

                        PublisherThread thread = new PublisherThread(message);
                        thread.start();
                        break;
                    }
                    case 3: {
                        String message = "musicOff 0";

                        PublisherThread thread = new PublisherThread(message);
                        thread.start();
                        break;
                    }
                }
            } catch (InterruptedException e) {
                return;
            } catch (Exception ex) {
                synchronized(AutomationSettings.class) {
                    AutomationSettings.getInstance().setThread(null);
                    //back to main thread
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            mainWindow.getButtonMusicOn().setEnabled(true);
                            mainWindow.getButtonMusicOff().setEnabled(true);
                            mainWindow.getButtonFlashOn().setEnabled(true);
                            mainWindow.getButtonFlashOff().setEnabled(true);
                            mainWindow.getButtonAutoOn().setEnabled(true);
                            mainWindow.getButtonAutoOff().setEnabled(false);
                            mainWindow.getFlashDurationList().setEnabled(true);
                            mainWindow.getMusicDurationList().setEnabled(true);
                            mainWindow.getButtonStartClassification().setEnabled(true);
                            mainWindow.appendToLog("Automatic mode disabled");
                        }
                    });
                }
                return;
            }
        } while (true);
    }
}
