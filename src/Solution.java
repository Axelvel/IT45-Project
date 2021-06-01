import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a solution to the problem
 * Assignation holds the number of the interface for the
 * formation represented by the index in the array
 * For instance, assignation[2] = 3 means that formation 2 was
 * assigned to interface 3
 */
public class Solution {

    private int nbFormations;
    private int[] assignation;

    public Solution(int nbFormations){
        this.nbFormations = nbFormations;
        assignation = new int[nbFormations];
    }

    /**
     * Get the interface assigned to a formation
     * @param f : formation id
     * @return interface id
     */
    public int getAssignation(int f){ return assignation[f]; }

    /**
     * Set the interface assigned to a formation
     * @param f : formation id
     * @param i : interface id
     */
    public void setAssignation(int f, int i){ assignation[f] = i; }

    /**
     * Check if a solution is valid
     * @return
     */
    public boolean isValid(){
        return true;
    }

    /**
     * Checks if the interface assigned to the formation has the right skill
     * @param f : formation id
     */
    public boolean isSkillValid(int f) {
        int id = this.getAssignation(f);
        int skill = Generator.getFormationArray()[f].getSkill();

        if (Generator.getInterfaceArray()[id].getSkill() == skill) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the interface assigned to the formation has the right specialty
     * @param f : formation id
     */
    public boolean isSpecialtyValid(int f) {
        int id = this.getAssignation(f);
        Speciality spe = Generator.getFormationArray()[f].getSpeciality();

        if (Generator.getInterfaceArray()[id].getSpecialities().contains(spe)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the generated schedule for the interface is valid
     * @param i : interface id
     */
    public boolean isScheduleValid(int i) {
        List<Formation> schedule = printSchedule(i);

        int hours = 0;
        int startday = schedule.get(0).getStartHour();
        int endDay;


        for (int j = 0; j < schedule.size(); j++) {


            hours += (schedule.get(j).getEndHour() - schedule.get(j).getStartHour());

            if (j != 0) {
                if (schedule.get(j).getDay() == schedule.get(j-1).getDay()) {
                    if (schedule.get(j-1).getEndHour() > schedule.get(j).getStartHour()) {
                        System.out.println(j);
                        System.out.println("Pas compatible");
                        return false;
                    } else {
                        endDay = schedule.get(j).getEndHour();

                        if (endDay - startday >= 12) {
                            System.out.println("Amplitude horraire journée dépassée");
                            return false;
                        }

                        if (hours >= 35) {
                            System.out.println("+35 heures semaine");
                            return false;
                        }

                        if (schedule.get(j).getStartHour() > 12 && (schedule.get(j).getStartHour() - schedule.get(j-1).getEndHour()) < 1) {
                            System.out.println("Bouffe");
                            return false;
                        }
                    }
                } else {
                    startday = schedule.get(j).getStartHour();
                }
            }

        }
        System.out.println("Hours : " + hours);
        return true;
    }

    /**
     * Returns the indexes of the formations assigned to an interface
     * @param i : interface id
     * @return list of indexes
     */
    public List<Integer> getInterfaceIndexes(int i) {
        List<Integer> indexList = new ArrayList<>();
        for (int j= 0; j < assignation.length; j++) {
            if (assignation[j] == i) {
                indexList.add(j);
            }
        }
        return indexList;
    }


    /**
     * Print the content of the assignation array
     */
    public void printAssignation(){
        for(int i = 0;i < nbFormations;i++){
            System.out.println("Formation "+i+" assigned to interface "+getAssignation(i));
        }
    }

    /**
     * Neatly prints the schedule of an interface
     * @param i : interface id
     */
    public List<Formation> printSchedule(int i){
        //pour chaque interface
            //ajouter dans une liste l'index des formations correspondantes
            //récupérer les infos de chaque formation
            //Former un emploi du temps avec les horaires de chaque formation

        List<Integer> indexList = getInterfaceIndexes(i);
        List<Formation> schedule = new ArrayList<>();

        for (int j = 0; j < indexList.size(); j++) {
            int n = indexList.get(j);
            Formation formation = Generator.getFormationArray()[n];
            int id = Generator.getFormationArray()[n].getId();
            Day day = formation.getDay();
            int start = formation.getStartHour();
            int end = formation.getEndHour();
            System.out.println(id + ": " + day + ", " + start + "-" + end);
            schedule.add(formation);
        }

        return schedule;
    }



    public int[] getAssignationArray(){ return assignation; }

    public int getNbFormations(){ return nbFormations; }

}

