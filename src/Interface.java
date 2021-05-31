import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class representing an interface
 * Each interface has a skill and a speciality
 */
public class Interface {

    private final int skill;
    private List<Speciality> specialities = new ArrayList<>(); //Array ?

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

    public int getSkill(){ return skill; }
    public List<Speciality> getSpecialities(){ return specialities; }
}
