
import java.util.List;

/**
 * Class representing an interface
 * Each interface has a skill and a speciality
 */
public class Interface {
    private final int id;
    private final int skill;
    private final List<Speciality> specialities;

    public Interface(int id, int skill, List<Speciality> specialities) {
        this.id = id;
        this.skill = skill;
        this.specialities = specialities;
    }

    public int getSkill(){ return skill; }
    public List<Speciality> getSpecialities(){ return specialities; }
    public int getId(){return id;}
    public String toString(){
        return "Skill : ("+skill+")\n Specialities : (" + specialities + ") ";
    }
}
