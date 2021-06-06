
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class representing an interface
 * Each interface has a skill and a speciality
 */
public class Interface {

    private final int skill;
    private final List<Speciality> specialities; //Array ?

    public Interface(int skill, List<Speciality> specialities) {
        this.skill = skill;
        this.specialities = specialities;
    }

    public void print() {
        AtomicReference<String> str = new AtomicReference<>(skill + ", ( ");
        specialities.forEach(element -> str.set(str + element.name() + " "));
        str.set(str + ")");
        System.out.println(str.get());
    }

    public String toString(){
        return "Skill : ("+skill+")\n Specialities : (" + specialities + ") ";
    }

    public int getSkill(){ return skill; }
    public List<Speciality> getSpecialities(){ return specialities; }
}
