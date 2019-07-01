package computation;

import queue.Queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ComputateMatches {

    private double[] entropy;
    private HashMap<String, Double[]> trainingSetHashMap;
    private int k = 11;

    public ComputateMatches(double[] entropy, HashMap<String, Double[]> trainingSetHashMap) {
        this.entropy = entropy;
        this.trainingSetHashMap = trainingSetHashMap;
    }

    private double distance(double[] x, double[] y) {
        double temp = 0;
        for (int i=0;i<x.length;i++) {
            temp += (x[i]-y[i])*(x[i]-y[i]);
        }

        return Math.sqrt(temp);
    }

    public int computate() {

        int i = 0;

        DistancePair[] distanceArray = new DistancePair[trainingSetHashMap.keySet().size()];

        for (Map.Entry<String, Double[]> entry : trainingSetHashMap.entrySet()) {
            if (!trainingSetHashMap.isEmpty() && trainingSetHashMap != null) {
                double[] x = entropy;
                Double[] tempy = entry.getValue();
                double[] y = new double[tempy.length];

                for (int j = 0; j < 14; j++) {
                    y[j] = tempy[j].doubleValue();
                }

                String key = entry.getKey();
                double distance = distance(x, y);

                distanceArray[i] = new DistancePair(key, distance);

                System.out.println("My key: " + entry.getKey());
                System.out.println("My distance: " + distance);
            }
            i++;
        }

        Arrays.sort(distanceArray);

        double weight_eyes_open = 0;
        double weight_eyes_closed = 0;
        int occurences_eyes_open = 0;
        int occurences_eyes_closed = 0;

        for (int j = 0; j < Math.min(k, distanceArray.length); j++) {
            if (distanceArray[j].category.equalsIgnoreCase("EyesClosed")) {
                occurences_eyes_closed++;
                weight_eyes_closed += 1.0 / (distanceArray[j].distance);
            } else {
                occurences_eyes_open++;
                weight_eyes_open += 1.0 / (distanceArray[j].distance);
            }
        }

        if (occurences_eyes_open * weight_eyes_open < occurences_eyes_closed * weight_eyes_closed) {
            return Queue.EYES_CLOSED;
        } else {
            return Queue.EYES_OPEN;
        }
    }
}
