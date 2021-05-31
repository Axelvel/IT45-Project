import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class Interface {

    private final int competence;
    private List<Specialite> specialites = new ArrayList<>(); //Array ?

    public Interface(int competence, List<Specialite> specialites) {
        this.competence = competence;
        this.specialites = specialites;
    }

    public void print() {
        AtomicReference<String> str = new AtomicReference<>(competence + ", ( ");
        specialites.forEach(element -> str.set(str + element.name() + " "));
        str.set(str + ")");
        System.out.println(str.get());
    }
}
