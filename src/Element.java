
public class Element {

    public Integer i;
    public Integer x;
    public Integer y;


    public Element(int i, int x, int y) {
        this.i = i;
        this.x = x;
        this.y = y;
    }

    public double distance() {
        return 1.0;
    }

    public void print() {
        System.out.println("i: " + i + " (x: " + x + ", y: " + y + ")");
    }



}
