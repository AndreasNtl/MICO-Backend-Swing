package readCSVfiles;

import entropy.Entropy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    private String csvFile;

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
    }

    public double[] readIt() {
        ArrayList<Double>[] lists = new ArrayList[14];
        for (int i = 0; i < 14; i++) {
            lists[i] = new ArrayList<>();
        }

        // String csvFile = "/home/christos/Desktop/anaptiksi/eclass/TS/Training Set.csv";
        String line = "";
        String cvsSplitBy = ",";
        int i = 0;

        int counter = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                counter++;
                // use comma as separator
                if (i > 0) {
                    String[] country = line.split(cvsSplitBy);
                    for (int k = 0; k < 14; k++) {
                        lists[k].add(Double.parseDouble(country[k]));
                    }
                }
                //System.out.println(i + "         " + country[4] + " ,      " + country[5] + "]" );
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (counter == 0) {
            return null;
        }


        Entropy entropyCalculator = new Entropy();

        double[] featureVector = new double[14];

        for (int j = 0; j < 14; j++) {
//            if (lists[j].size() == 0) {
//                System.out.println("===================================== ignoring list ... " + j) ;
//                continue;
//            }
            Double[] array = lists[j].toArray(new Double[lists[j].size()]);
            double[] primitiveArray = new double[array.length];

            for (int k = 0; k < array.length; k++) {
                primitiveArray[k] = array[k].doubleValue();
            }

            featureVector[j] = entropyCalculator.calculateEntropy(primitiveArray);
        }

        return featureVector;
    }
}
