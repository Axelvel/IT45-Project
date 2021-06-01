import java.util.HashMap;
import java.util.Map;

public enum Day {
    MONDAY (0),
    TUESDAY (1),
    WEDNESDAY (2),
    THURSDAY (3),
    FRIDAY (4),
    SATURDAY (5);

    public final int id;
    private static final Map<Integer, Day> byId = new HashMap<>();
    static {
        for (Day e: values()) {
            byId.put(e.id, e);
        }
    }

    Day(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public static Day valueOfId(int id) {
        return byId.get(id);
    }
}
