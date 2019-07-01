package threads;

import autostate.AutomationSettings;
import gui.MainWindow;
import settings.AdministratorSettings;

import javax.swing.*;
import java.util.Random;

public class AndroidThread extends Thread {
    private MainWindow mainWindow;

    public AndroidThread(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void run() {
        super.run();

        do {
            try {
                int t;

                synchronized(AutomationSettings.class) {
                    t = AdministratorSettings.getInstance().getAndroid_frequency();
                }

                if (t == 0) {
                    t = 1000;
                }

                Thread.sleep(t);

                switch (new Random().nextInt(4)) {
                    case 0: {
                        int duration = 1 + (new Random().nextInt(10));
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
                synchronized (AutomationSettings.class) {
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
                            mainWindow.appendToLog("Automatic mode disabled");
                        }
                    });
                }
                return;
            }
        } while (true);
    }
}
