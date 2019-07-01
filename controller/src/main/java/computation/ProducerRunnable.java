package computation;

import gui.MainWindow;
import queue.Queue;
import readCSVfiles.CSVReader;
import readCSVfiles.TS_CSVreader;
import settings.AdministratorSettings;

import java.io.File;
import java.util.HashMap;


//Producer Class in java
public class ProducerRunnable implements Runnable {

    private final Queue sharedQueue;
    private ComputateMatches match;
    private CSVReader dataFinalreader;
    private TS_CSVreader tsreader;
   // private String dataFinalCSV = "/home/andreas/Desktop/anaptiksi/eclass/DataFinal";
   // private String tsCSV = "/home/andreas/Desktop/anaptiksi/eclass/TS/Training Set.csv";
    private HashMap<String,Double[]> myVectorHashMap;
    private MainWindow mainWindow;

    public ProducerRunnable(Queue queue, MainWindow mainWindow) {
        this.sharedQueue = queue;
        this.mainWindow = mainWindow;
    }

    @Override
    public void run() {
        final File folder = new File(AdministratorSettings.getInstance().getDataFinalCSV());

        tsreader = new TS_CSVreader(AdministratorSettings.getInstance().getTsCSV());
        myVectorHashMap = tsreader.readIt();

        int success_computation = 0;
        int failed_computation = 0;
        int opened_theory = 0;
        int closed_theory = 0;
        int opened_calculated = 0;
        int closed_calculated = 0;

        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {

                dataFinalreader = new CSVReader(fileEntry.getAbsolutePath());
                double[] myEntropy = dataFinalreader.readIt();
                if (myEntropy == null) {
                    continue;
                }

                match = new ComputateMatches(myEntropy, myVectorHashMap);
                int m = match.computate();

                String name = fileEntry.getName();
                String theoreticValue = name.substring(name.indexOf('.') + 1, name.indexOf('.') + 11);

                System.out.println(theoreticValue);
                mainWindow.appendToLog(theoreticValue);

                if (theoreticValue.equalsIgnoreCase("EyesOpened")) {
                    opened_theory++;
                }
                if (theoreticValue.equalsIgnoreCase("EyesClosed")) {
                    closed_theory++;
                }
                switch(m) {
                    case Queue.EYES_OPEN:
                        opened_calculated++;
                        if (theoreticValue.equalsIgnoreCase("EyesOpened")) {
                            success_computation++;
                        } else {
                            failed_computation++;
                        }
                        break;
                    case Queue.EYES_CLOSED:
                        closed_calculated++;
                        if (theoreticValue.equalsIgnoreCase("EyesClosed")) {
                            success_computation++;
                        } else {
                            failed_computation++;
                        }
                        break;
                }
                sharedQueue.put(m);

            } else {
                System.out.println(fileEntry.getName());
                mainWindow.appendToLog(fileEntry.getName());
            }

            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }

        System.out.println(" Classification Statistics");
        System.out.println("--------------------------");
        System.out.println(" Total computations: " + (success_computation + failed_computation));
        System.out.println(" Computation hits: " + success_computation);
        System.out.println(" Computation misses: " + failed_computation);
        System.out.println(" Classified open: " + opened_calculated);
        System.out.println(" Dataset open: " + opened_theory);
        System.out.println(" Classified close: " + closed_calculated);
        System.out.println(" Dataset close: " + closed_theory);
        System.out.println(" Success rate: " + 100*success_computation/(float)(success_computation + failed_computation) + "%");
        System.out.println();


        mainWindow.appendToLog(" Classification Statistics");
        mainWindow.appendToLog("===============");
        mainWindow.appendToLog(" Total computations: " + (success_computation + failed_computation));
        mainWindow.appendToLog(" Computation hits: " + success_computation);
        mainWindow.appendToLog(" Computation misses: " + failed_computation);
        mainWindow.appendToLog(" Classified open: " + opened_calculated);
        mainWindow.appendToLog(" Dataset open: " + opened_theory);
        mainWindow.appendToLog(" Classified close: " + closed_calculated);
        mainWindow.appendToLog(" Dataset close: " + closed_theory);
        mainWindow.appendToLog(" Success rate: " + 100*success_computation/(float)(success_computation + failed_computation) + "%");

        mainWindow.updateClassificationGui();
    }



}

