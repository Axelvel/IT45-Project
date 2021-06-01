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
        int skill = Generator.getFormationList().get(f).getSkill();

        if (Generator.getInterfaceList().get(id).getSkill() == skill) {
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
        Speciality spe = Generator.getFormationList().get(f).getSpeciality();

        if (Generator.getInterfaceList().get(id).getSpecialities().contains(spe)) {
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
    public void printSchedule(int i){
        //pour chaque interface
            //ajouter dans une liste l'index des formations correspondantes
            //récupérer les infos de chaque formation
            //Former un emploi du temps avec les horaires de chaque formation

        List<Integer> indexList = getInterfaceIndexes(i);

        for (int j = 0; j < indexList.size(); j++) {
            int n = indexList.get(j);
            Formation formation = Generator.getFormationList().get(n);
            Day day = formation.getDay();
            int start = formation.getStartHour();
            int end = formation.getEndHour();
            System.out.println(n + ": " + day + ", " + start + "-" + end);
        }
    }

    public int[] getAssignationArray(){ return assignation; }

    public int getNbFormations(){ return nbFormations; }

}

