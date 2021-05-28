
public class Formation {

    private final Specialite specialite;
    private final int competence;
    private final Day day;
    private final int beginning;
    private final int end;

    public Formation(Specialite specialite, int competence, Day day, int beginning, int end) {
        this.specialite = specialite;
        this.competence = competence;
        this.day = day;
        this.beginning = beginning;
        this.end = end;
    }

    public void print() {
        System.out.println(specialite + ", " + competence + ", " + day + ", " + beginning + ", " + end);
    }

}
