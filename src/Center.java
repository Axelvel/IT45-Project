/**
 * Class representing a formation center
 * Each center is specialized in one skill
 */
public class Center {

    private final Utils.Pair<Integer, Integer> coord;
    private final Speciality speciality;

    public Center(Utils.Pair<Integer, Integer> coord, Speciality speciality) {
        this.coord = coord;
        this.speciality = speciality;
    }

    public Utils.Pair<Integer, Integer> getCoord() {
        return coord;
    }

    public void print() {
        System.out.println(speciality + " : (" + coord.x + "," + coord.y + ")");
    }

    public String toString(){
        return speciality+" : (" + coord.x + ", " + coord.y + ")";
    }
    public Speciality getSpeciality(){ return speciality; }
    public Utils.Pair<Integer, Integer> getCoordinates(){ return coord; }
}
