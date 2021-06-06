import java.util.Arrays;
import java.util.List;

/**
 * Implement the tabu class, used to resolve our optimization problem
 * TODO : more explanations
 */
public class Tabu {

    private Solution bestSolution;
    private double[][] matrix;
    private List<Integer> tabuList; //TODO: Modify
    private final int tabuLength;

    public Tabu(int tabuLength) {
        this.tabuLength = tabuLength;
    }

    /**
     * Heuristic method, TODO : more explanations flemme
     * @param dist
     * @param spe
     * @return
     */
    public double heuristic(double dist, boolean spe) {
        if(spe){
            return dist;
        }else{
            return 0;
        }
    }

    /**
     * Creates a matrix with a given solution
     * Each line corresponds to a formation, and each column to an interface
     * The value of matrix[formation][interface] is the heuristic value of that position if
     * the interface manages the formation, -infinity if not
     * @param sol : a solution
     * @return mvmtMatrix : a double[][] matrix
     */
    public double[][] computeMatrix(Solution sol){ //TODO: optimiser (étant donné que les formations sont en ordre croissant on peut optimiser l'algo)
        double[][] mvmtMatrix = new double[Generator.NBR_FORMATIONS][Generator.NBR_INTERFACES];
        for (double[] row : mvmtMatrix)
            Arrays.fill(row, -1);
        double dist = 0;
        boolean spe = false;


        for(int i = 0;i<Generator.NBR_INTERFACES;i++){
            //On récupère le schedule de l'interface
            List<Formation> schedule = sol.generateSchedule(i);

            //Pour chaque jour
            for(Day day : Day.values()){
                //Coordonnées du centre de départ
                Center prevCenter = Generator.getCenterArray()[0];

                for(Formation f : schedule){
                    if(f.getDay() == day){
                        Center currentCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                        dist = Utils.calculateDist(prevCenter,currentCenter);
                        spe = sol.isSpecialtyValid(f.getId());
                        //calculate heuristic
                        mvmtMatrix[f.getId()][i] = heuristic(dist, spe);
                        prevCenter = currentCenter;
                    }
                }
                dist = Utils.calculateDist(prevCenter,Generator.getCenterArray()[0]);
                //TODO : find a way to add the last center heuristic
            }
        }

        return mvmtMatrix;
    }

    /**
     * Find solutions close to the one given in the parameters by trying to
     * optimize it
     * @param currentSol : current solution
     * @return neighborSol : list of better solution
     */
    public List<Solution> computeNeighborhood(Solution currentSol){
        List<Solution> neighborhood = null;

        //Calculer la matrice
        double[][] solMatrix = computeMatrix(currentSol);
        //A l'aide de cette matrice, trouver des solutions voisines
        //Si l'eval de ces solutions est meilleure, ajouter à la lsite
        
        return neighborhood;
    }

    /**
     * Add a movement to the tabu list
     * @param i : TODO : ?
     */
    public void addToTabuList(int i) {
        tabuList.add(i);
        if (tabuList.size() >= tabuLength) {
            tabuList.remove(0);
        }
    }

    /**
     * Find the fastest and easiest valid solution, without caring about
     * optimization. Mostly used for test.
     * @return a possible solution
     */
    public Solution getClosestNeighborSol(){
        //TODO : implement an easy but valid solution for test purposes
        Solution closestNeighborSol = new Solution(Generator.NBR_FORMATIONS);

        for(int i = 0;i < Generator.NBR_FORMATIONS;i++){
            for(int j = 0; j < Generator.NBR_INTERFACES;j++){
                closestNeighborSol.setAssignation(i,j);

                if(!closestNeighborSol.isScheduleValid(j)){
                    closestNeighborSol.setAssignation(i, -1);
                }else{
                    break;
                }
            }
        }

        return closestNeighborSol;
    }

}
