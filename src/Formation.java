/**
 * Class representing a formation
 * Each formation has a speciality, a skill, a day,
 * and a start and end time.
 */
public class Formation {

    private final Speciality speciality;
    private final int skill;
    private final Day day;
    private final int start;
    private final int end;

    public Formation(Speciality speciality, int skill, Day day, int start, int end) {
        this.speciality = speciality;
        this.skill = skill;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public void print() {
        System.out.println(speciality + ", " + skill + ", " + day + ", " + start + ", " + end);
    }

    public Speciality getSpeciality(){ return speciality; }
    public int getSkill(){ return skill; }
    public Day getDay(){ return day; }
    public int getStartHour(){ return start; }
    public int getEndHour(){ return end; }
}
