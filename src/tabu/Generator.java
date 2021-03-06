package tabu;
import java.util.*;
import models.*;
import utils.*;

/**
 * Generator class used to create an instance of a problem
 */
public class Generator {

    public static int NBR_APPRENANTS;

    public static int DIMENSION_ZONE_GEOGRAPHIQUE = 200;

    public static int NBR_INTERFACES;
    public static int NBR_FORMATIONS;

    public static int NBR_CENTRES_FORMATION = 5;
    public static int NBR_SPECIALITES = NBR_CENTRES_FORMATION;

    private static Interface[] interfaceArray;
    private static  Center[] centerArray;
    private static Formation[] formationArray;

    private final Random rand;


    public Generator(int nbApprenants) {
        NBR_APPRENANTS = nbApprenants;
        NBR_INTERFACES = (int) (NBR_APPRENANTS/4 * 1.2);
        NBR_FORMATIONS = NBR_APPRENANTS;
        interfaceArray = new Interface[NBR_INTERFACES];
        centerArray = new Center[NBR_CENTRES_FORMATION + 1];
        formationArray = new Formation[NBR_FORMATIONS];

        rand = new Random();
        generateInterfaces();
        generateCenters();
        generateFormations();
    }

    public static Interface[] getInterfaceArray(){ return interfaceArray; }
    public static Center[] getCenterArray(){return centerArray;}
    public static Formation[] getFormationArray(){return formationArray;}
    public static Center getCenterBySpeciality(int i){return centerArray[i];}

    /**
     * Fill the interface array with randomly generated interfaces
     */
    private void generateInterfaces() {
        for (int i = 0; i < NBR_INTERFACES; i++) {
            interfaceArray[i] = new Interface(i,generateCompetence(), generateSpecialities());
        }
    }

    /**
     * Generate a random competence between sign language (0) and lpc coding (1), or both (2)
     * @return competence id
     */
    private int generateCompetence() {
        double random = rand.nextDouble();
        if (random < 0.1) {
            return 2; //Both
        } else if (random < 0.55) {
            return 0; //Competence 1
        }  else {
            return 1; //Competence 2
        }
    }

    /**
     * Generate a random list of specialities
     * @return specialities list
     */
    private List<Speciality> generateSpecialities() {
        List<Speciality> list = new ArrayList<>();

        for (int i = 0; i < NBR_SPECIALITES; i++) {
            if (rand.nextDouble() < 0.2) {
                list.add(Speciality.valueOfId(i));
            }
        }
        return list;
    }

    /**
     * Fill the center array with centers with randomly generated coordinates
     */
    private void generateCenters() {

        for (int i = 0; i <= NBR_CENTRES_FORMATION; i++) {
            //Generate coordinates
            int x = (int) (rand.nextDouble() * DIMENSION_ZONE_GEOGRAPHIQUE);
            int y = (int) (rand.nextDouble() * DIMENSION_ZONE_GEOGRAPHIQUE);
            Pair<Integer, Integer> coord = new Pair<>(x,y);

            Speciality speciality;

            if (i == 0) {
                speciality = null; //Initial center
            } else {
                speciality = Speciality.valueOfId(i-1);
            }
            centerArray[i] = new Center(coord, speciality);
        }
    }

    /**
     * Fill the formation array with randomly generates formations, then sort them
     * in chronological order
     */
    private void generateFormations() {
        for (int i = 0; i < NBR_FORMATIONS; i++) {

            int random;

            int competence = rand.nextInt(2);
            Speciality speciality = Speciality.valueOfId(rand.nextInt(NBR_SPECIALITES));

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
     * Used two comparators in order to use
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

    /**
     * Print a generator instance
     */
    public void printInstance(){
        System.out.println("\nFormations : " + getFormationArray().length + "\n");
        for(int i = 0;i<getFormationArray().length;i++){ System.out.println(getFormationArray()[i]); }

        System.out.println("\nInterfaces : " + getInterfaceArray().length + "\n");
        for(int i = 0;i<getInterfaceArray().length;i++){ System.out.println(getInterfaceArray()[i]); }

        System.out.println("\nCentres : " + getCenterArray().length + "\n");
        for(int i = 0;i<getCenterArray().length;i++){System.out.println(getCenterArray()[i]); }

        System.out.println("\nCentres : " + getCenterArray().length + "\n");
        for(int i = 0;i<getCenterArray().length;i++){System.out.println(getCenterArray()[i]); }

    }

}
