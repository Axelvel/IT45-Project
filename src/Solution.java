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
     * Print the content of the assignation array
     */
    public void printAssignation(){
        for(int i = 0;i < nbFormations;i++){
            System.out.println("Formation "+i+" assigned to interface "+getAssignation(i));
        }
    }

    /**
     * Print neatly the schedule of an interface
     * @param i : interface id
     */
    public void printSchedule(int i){
        //pour chaque interface
            //ajouter dans une liste l'index des formations correspondantes
            //récupérer les infos de chaque formation
            //Former un emploi du temps avec les horaires de chaque formation
    }

    public int[] getAssignationArray(){ return assignation; }

    public int getNbFormations(){ return nbFormations; }
}

