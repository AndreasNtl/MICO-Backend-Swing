package readCSVfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TS_CSVreader {

    private String csvFile;

    public TS_CSVreader(String csvFile) {
        this.csvFile = csvFile;
    }

    public HashMap<String, Double[]> readIt() {
        HashMap<String, Double[]> myVectorHashMap = new HashMap<>();

        // String csvFile = "/home/christos/Desktop/anaptiksi/eclass/TS/Training Set.csv";
        String line = "";
        String cvsSplitBy = ",";
        int i =0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (i > 0) {
                    // use comma as separator
                    String[] currentLine = line.split(cvsSplitBy);
                    Double[] data = new Double[14];

                    for (int j = 0; j < 14; j++) {
                        data[j] = Double.parseDouble(currentLine[j + 1]);
                    }

                    myVectorHashMap.put(currentLine[0] + "_" + i, data);
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return myVectorHashMap;

    }
}
