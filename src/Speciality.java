import java.util.HashMap;
import java.util.Map;

public enum Speciality {
    MENUISERIE (0),
    ELECTRICITE (1),
    MECANIQUE (2),
    INFORMATIQUE (3),
    CUISINE (4);

    public final int id;
    private static final Map<Integer, Speciality> byId = new HashMap<>();
    static {
        for (Speciality e: values()) {
            byId.put(e.id, e);
        }
    }

    Speciality(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static Speciality valueOfId(int id) {
        return byId.get(id);
    }
}
