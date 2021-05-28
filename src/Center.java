
public class Center {

    private final Pair<Integer, Integer> coord;
    private final Specialite specialite;

    public Center(Pair<Integer, Integer> coord, Specialite specialite) {
        this.coord = coord;
        this.specialite = specialite;
    }

    public void print() {
        System.out.println(specialite + " : (" + coord.x + "," + coord.y + ")");
    }

}
