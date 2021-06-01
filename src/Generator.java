
import java.util.*;
import java.util.stream.IntStream;

public class Generator {

    public static int NBR_APPRENANTS = 80;

    public static int DIMENSION_ZONE_GEOGRAPHIQUE = 200;

    public static int NBR_INTERFACES = (int) (NBR_APPRENANTS/4 * 1.2);
    public static int NBR_FORMATIONS = NBR_APPRENANTS;

    public static int NBR_CENTRES_FORMATION = 5;
    public static int NBR_SPECIALITES = NBR_CENTRES_FORMATION;

    private final Interface[] interfaceArray = new Interface[NBR_INTERFACES];
    private final Center[] centerArray = new Center[NBR_CENTRES_FORMATION + 1];
    private final Formation[] formationArray = new Formation[NBR_FORMATIONS];

    private final Random rand;

    public Generator() {
        rand = new Random();
        generateInterfaces();
        generateCenters();
        generateFormations();
    }

    public Interface[] getInterfaceArray(){ return interfaceArray; }
    public Center[] getCenterArray(){return centerArray;}
    public Formation[] getFormationArray(){return formationArray;}

    private void generateInterfaces() {
        for (int i = 0; i < NBR_INTERFACES; i++) {
            interfaceArray[i] = new Interface(generateCompetence(), generateSpecialities());
        }
    }

    private int generateCompetence() {
        double random = rand.nextDouble();
        if (random < 0.1) {
            return 2; //Les 2
        } else if (random < 0.55) {
            return 0; //Competence 1
        }  else {
            return 1; //Competence 2
        }
    }

    private List<Speciality> generateSpecialities() {

        List<Speciality> list = new ArrayList<>();

        for (int i = 0; i < NBR_SPECIALITES; i++) {
            if (rand.nextDouble() < 0.2) {
                list.add(Speciality.valueOfId(i));
            }
        }
        return list;
    }


    private void generateCenters() {

        for (int i = 0; i <= NBR_CENTRES_FORMATION; i++) {

            //Generate coord
            int x = (int) (rand.nextDouble() * DIMENSION_ZONE_GEOGRAPHIQUE);
            int y = (int) (rand.nextDouble() * DIMENSION_ZONE_GEOGRAPHIQUE);
            Pair coord = new Pair(x,y);

            Speciality speciality;

            if (i == 0) {
                speciality = null; //Initial center
            } else {
                speciality = Speciality.valueOfId(i-1);
            }
            centerArray[i] = new Center(coord, speciality);
        }
    }

    private void generateFormations() {

        for (int i = 0; i < NBR_FORMATIONS; i++) {

            int random;

            Speciality speciality = Speciality.valueOfId(rand.nextInt(NBR_SPECIALITES));

            int competence = rand.nextInt(2);

            random = rand.nextInt(6);
            Day day = Day.valueOfId(random);

            int morning = rand.nextInt(2);
            int beginning, end;

            if (morning == 1) {
                beginning = 8 + rand.nextInt(3);
                end = beginning + rand.nextInt(11 - beginning) + 2;
            } else {
                beginning = 13 + rand.nextInt(4);
                end = beginning + rand.nextInt(18 - beginning) + 2;
            }

            formationArray[i] = new Formation(i,speciality, competence, day, beginning, end);
        }
        sortFormations();
    }

    /**
     * Function used to sort the formations array according
     * to day and start day
     * Used two comparators in order to user
     * the Arrays.sort() method
     */
    public void sortFormations(){
        class SortByDay implements Comparator<Formation>
        {
            public int compare(Formation a, Formation b)
            {
                return a.getDay().getId() - b.getDay().getId();
            }
        }

        class SortByHour implements Comparator<Formation>
        {
            public int compare(Formation a, Formation b)
            {
                if(a.getDay() == b.getDay()){ return a.getStartHour() - b.getStartHour(); } else{ return 0; }
            }
        }

        Arrays.sort(formationArray, new SortByDay());
        Arrays.sort(formationArray, new SortByHour());
    }

}
