import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing a solution to the problem
 * Assignation holds the number of the interface for the
 * formation represented by the index in the array
 * For instance, assignation[2] = 3 means that formation 2 was
 * assigned to interface 3
 */
public class Solution {

    private int[] assignation;

    public Solution(){
        this.assignation = new int[Generator.NBR_FORMATIONS];
        Arrays.fill(this.assignation, -1);
    }

    public Solution(Solution sol) {
        this.assignation = sol.assignation.clone();
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
     * @return true if valid, false if not
     */
    public boolean isValid(){
        //check if all formations have been assigned
        if(Utils.contains(assignation,-1)) return false;

        //check if all schedules are valid
        for(Interface i: Generator.getInterfaceArray()){
            Schedule s = new Schedule(i, this);
            if(!s.isScheduleValid()) return false;
        }
        /*for(int i = 0;i < Generator.NBR_INTERFACES;i++){
            if(!isScheduleValid(i)) return false;
        }*/

        return true; }

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

        return Generator.getInterfaceArray()[id].getSpecialities().contains(spe);
    }


    /**
     * Returns the indexes of the formations assigned to an interface
     * @param i : interface id
     * @return list of indexes
     */
    public List<Integer> getInterfaceIndexes(Interface i) {
        List<Integer> indexList = new ArrayList<>();
        for (int j= 0; j < assignation.length; j++) {
            if (assignation[j] == i.getId()) {
                indexList.add(j);
            }
        }
        return indexList;
    }


    /**
     * Print the content of the assignation array
     */
    public void printAssignation(){
        for(int i = 0;i < Generator.NBR_FORMATIONS;i++){
            System.out.println("F"+i+" - "+getAssignation(i));
        }
    }

    /**
     * Neatly prints the schedule of an interface
     * @param i : interface id
     */
    public List<Formation> generateSchedule(Interface i){
        List<Integer> indexList = getInterfaceIndexes(i);
        List<Formation> schedule = new ArrayList<>();

        for (int n : indexList) {
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
        for(Interface i:Generator.getInterfaceArray()){
            distByInterface[i.getId()] = 0;
            List<Formation> schedule = generateSchedule(i);
            Center prevCenter = Generator.getCenterArray()[0];
            for(Formation f : schedule){
                Center currentCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                distByInterface[i.getId()] += Utils.calculateDist(prevCenter,currentCenter);
                prevCenter = currentCenter;
            }
            distByInterface[i.getId()] += Utils.calculateDist(prevCenter,Generator.getCenterArray()[0]);
            totalDist += distByInterface[i.getId()];
        }

/*
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
*/
        double avrDist = totalDist/Generator.NBR_INTERFACES;
        double factor = totalDist/Generator.NBR_FORMATIONS;
        double strdDev;
        double varDist = 0;

        for(double dist: distByInterface){varDist += Math.pow(avrDist-dist,2); }
        strdDev = Math.sqrt(varDist/Generator.NBR_INTERFACES);

        return 0.5 * (avrDist + strdDev) + 0.5 * factor * penalties;
    }

    /**
     * Test function used to see the details of a solution, including the
     * list of interfaces that aren't assigned to any formations, an
     * evaluation of the solution, and examples of schedules of a few interfaces
     * and their work time
     */
    public void showSolutionDetails(){
        System.out.println("##### SOLUTION DETAILS #####");
        //list which interface don't have any formations
        System.out.println("Interfaces assigned to a formation : ");
        for(int i = 0; i< Generator.NBR_INTERFACES;i++){
            if(Utils.contains(assignation,i)){
                System.out.print(i + " - ");
            }
        }

        System.out.println("\nInterfaces not assigned to a formation : ");
        for(int i = 0; i< Generator.NBR_INTERFACES;i++){
            if(!Utils.contains(assignation,i)){
                System.out.print(i + " - ");
            }
        }

        //give the eval of a solution
        System.out.println("\nValue of the solution :");
        System.out.println(evalSolution());
        //showSolutionStats();

    }

    public void showSolutionStats(){
        int nbSpecialityValid = 0;
        int nbSkillValid = 0;

        for(Formation f: Generator.getFormationArray()){
            if(isSkillValid(f.getId())){nbSkillValid++;}
        }

        //System.out.println("Number of valid specialities : "+nbSpecialityValid+"/"+Generator.NBR_FORMATIONS);
        System.out.println("Number of valid skills : "+nbSkillValid+"/"+Generator.NBR_FORMATIONS);
    }

    public int[] getAssignationArray(){ return assignation; }

}

