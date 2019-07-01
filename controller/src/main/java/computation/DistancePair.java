package computation;

public class DistancePair implements Comparable<DistancePair> {
    public final String key;
    public final double distance;
    public final String category;

    public DistancePair(String key, double distance) {
        this.key = key;
        this.distance = distance;
        this.category = key.split("_")[0];
    }

    @Override
    public int compareTo(DistancePair o) {
        if (distance < o.distance) {
            return -1; // (int) -Math.round(Math.abs(distance-o.distance));
        } else if (distance == o.distance) {
            return 0;
        } else {
            return 1;// (int) +Math.round(Math.abs(distance-o.distance));
        }
    }
}
