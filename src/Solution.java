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

    private final int nbFormations;
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
        List<Formation> schedule = generateSchedule(i);

        int hours = 0;

        for (int j = 0; j + 1 < schedule.size(); j++) {
            if (schedule.get(j).getEndHour() > schedule.get(j+1).getStartHour()) {
                return false;
            } else {
                hours += schedule.get(j).getEndHour() - schedule.get(j).getStartHour();

                if (hours >= 35) {
                    return false;
                }

                if (schedule.get(j).getStartHour() > 12 && (schedule.get(j).getStartHour() - schedule.get(j-1).getEndHour()) < 1) {
                    return false;
                }
            }
        }
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
    public List<Formation> generateSchedule(int i){
        List<Integer> indexList = getInterfaceIndexes(i);
        List<Formation> schedule = new ArrayList<>();

        for (int j = 0; j < indexList.size(); j++) {
            int n = indexList.get(j);
            Formation formation = Generator.getFormationArray()[n];
            schedule.add(formation);
        }

        return schedule;
    }

    /**
     * Compute the evaluation of a solution according to the
     * average distance ran by an employee, the standard deviation,
     * the average distance ran by all employees and the penalties
     * @return eval value
     */
    public double evalSolution(){
        int penalties = 0;
        double totalDist = 0;
        double[] distByInterface = new double[Generator.NBR_INTERFACES];

        //Compute penalties
        for(int i = 0; i < assignation.length;i++){ if(!isSpecialtyValid(i)){penalties++;} }

        //Compute total distance ran by an interface and total distance ran by all interfaces
        for(int i = 0;i<Generator.NBR_INTERFACES;i++){
            distByInterface[i] = 0;
            List<Formation> schedule = generateSchedule(i);
            Center prevCenter = Generator.getCenterArray()[0];
            for(Formation f : schedule){
                Center currentCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                distByInterface[i] += Utils.calculateDist(prevCenter,currentCenter);
                prevCenter = currentCenter;
            }
            distByInterface[i] += Utils.calculateDist(prevCenter,Generator.getCenterArray()[0]);
            totalDist += distByInterface[i];
        }

        double avrDist = totalDist/Generator.NBR_INTERFACES;
        double factor = totalDist/Generator.NBR_FORMATIONS;
        double strdDev;
        double varDist = 0;

        for(double dist: distByInterface){varDist += Math.pow(avrDist-dist,2); }
        strdDev = Math.sqrt(varDist/Generator.NBR_INTERFACES);

        return 0.5 * (avrDist + strdDev) + 0.5 * factor * penalties;
    }

    public int[] getAssignationArray(){ return assignation; }
    public int getNbFormations(){ return nbFormations; }

}

