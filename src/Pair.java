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

    public void print() {
        System.out.println("(x: " + x + ",y: " + y + ")");
    }


}
