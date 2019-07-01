import gui.MainWindow;
import settings.AdministratorSettings;

import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            AdministratorSettings.getInstance().loadFromDisk();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow gui = new MainWindow();
                gui.setVisible(true);
            }
        });
    }
}
