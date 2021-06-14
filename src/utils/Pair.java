package utils;

/**
 * Class representing a pair of number
 * Has multiple uses
 *
 * @param <X> : first element
 * @param <Y> : second element
 */
public class Pair<X, Y> {

    public X x;
    public Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {
        this.x = null;
        this.y = null;
    }

    public String toString() {
        return "(x: " + x + ",y: " + y + ")";
    }
}
