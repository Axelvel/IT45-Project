
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public static int NBR_APPRENANTS = 80;

    public static int DIMENSION_ZONE_GEOGRAPHIQUE = 200;

    public static int NBR_INTERFACES = (int) (NBR_APPRENANTS/4 * 1.2);
    public static int NBR_FORMATIONS = NBR_APPRENANTS;

    public static int NBR_CENTRES_FORMATION = 5;
    public static int NBR_SPECIALITES = NBR_CENTRES_FORMATION;

    private final Day[] days = Day.values();
    private final Specialite[] specialites = Specialite.values();

    public List<Interface> interfaceList = new ArrayList<>();
    public List<Center> centerList = new ArrayList<>();
    public List<Formation> formationList = new ArrayList<>();

    private final Random rand;



    public Generator() {
        rand = new Random();
        generateInterfaces();
        generateCenters();
        generateFormations();
    }


    public void generateInterfaces() {
        for (int i = 0; i < NBR_INTERFACES; i++) {
            interfaceList.add(new Interface(generateCompetence(), generateSpecialites()));
        }
    }

    public int generateCompetence() {
        double random = rand.nextDouble();
        if (random < 0.1) {
            return 2; //Les 2
        } else if (random < 0.55) {
            return 0; //Competence 1
        }  else {
            return 1; //Competence 2
        }
    }

    public List<Specialite> generateSpecialites() {

        List<Specialite> list = new ArrayList<>();

        for (int i = 0; i < NBR_SPECIALITES; i++) {
            if (rand.nextDouble() < 0.2) {
                list.add(specialites[i]);
            }
        }
        return list;
    }



    public void generateCenters() {

        for (int i = 0; i <= NBR_CENTRES_FORMATION; i++) {

            //Generate coord
            int x = (int) (rand.nextDouble() * DIMENSION_ZONE_GEOGRAPHIQUE);
            int y = (int) (rand.nextDouble() * DIMENSION_ZONE_GEOGRAPHIQUE);
            Pair coord = new Pair(x,y);

            int random;
            Specialite specialite;

            if (i == 0) {
                specialite = null; //Initial center
            } else {
                specialite = specialites[i-1]; //Formation centers
            }

            centerList.add(new Center(coord, specialite));
        }
    }

    public void generateFormations() {

        for (int i = 0; i < NBR_FORMATIONS; i++) {

            int random;

            Specialite specialite = specialites[rand.nextInt(NBR_SPECIALITES)];

            int competence = rand.nextInt(2);

            random = rand.nextInt(6);
            Day day = days[random];

            int morning = rand.nextInt(2);
            int beginning, end;

            if (morning == 1) {
                beginning = 8 + rand.nextInt(3);
                end = beginning + rand.nextInt(11 - beginning) + 2;
            } else {
                beginning = 13 + rand.nextInt(4);
                end = beginning + rand.nextInt(18 - beginning) + 2;
            }

            formationList.add(new Formation(specialite, competence, day, beginning, end));
        }

    }


}