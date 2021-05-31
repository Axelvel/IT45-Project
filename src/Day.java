public enum Day {
    MONDAY (1),
    TUESDAY (2),
    WEDNESDAY (3),
    THURSDAY (4),
    FRIDAY (5),
    SATURDAY (6);

    public final int id;

    Day(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
