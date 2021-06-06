import java.util.Arrays;
import java.util.List;
import java.util.Timer;

/**
 * Implement the tabu class, used to resolve our optimization problem
 * TODO : more explanations
 */
public class Tabu {

    private Solution bestSolution;
    private double[][] matrix;
    private List<Pair<Integer, Integer>> tabuList;
    private int tabuLength;


    public Solution Tabu(Solution sol, int tabuLength, long t) {

        tabuList.clear();

        if (sol.isValid()) {
            bestSolution = sol;
        } else {
            System.out.println("Initial solution is not valid");
            return sol;
        }

        if (tabuLength > 0) {
            this.tabuLength = tabuLength;
        } else {
            System.out.println("tabuLength needs to be > 0");
            return sol;
        }

        //Compute matrix
        //Determine best move to make and check the tabuList
        //Change the current solution
        //Check if eval(solution) > eval(bestSolution)
        //When time is up, stop the search

        long startingTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startingTime < 10000) {
            //System.out.println(System.currentTimeMillis() - startingTime);
            //Start new Thread
        }
        //Kill the Thread
        System.out.println("Done");

        return bestSolution;
    }


    /**
     * Heuristic method, TODO : more explanations flemme
     * @param dist
     * @param spe
     * @return
     */
    public double heuristic(double dist, boolean spe) {

        float coefficient;

        if (spe) {
            coefficient = 1;
        } else {
            coefficient = (float) 1.4;
        }

        return coefficient * dist;
    }



    /**
     * Creates a matrix with a given solution
     * Each line corresponds to a formation, and each column to an interface
     * The value of matrix[formation][interface] is the heuristic value of that position if
     * the interface manages the formation, -infinity if not
     * @param sol : a solution
     * @return mvmtMatrix : a double[][] matrix
     */
    public double[][] computeMatrix(Solution sol){
        double[][] mvmtMatrix = new double[Generator.NBR_FORMATIONS][Generator.NBR_INTERFACES];
        Arrays.fill(mvmtMatrix,Double.NEGATIVE_INFINITY); //TODO : vérifie si ça marche lol
        double dist = 0;
        boolean spe = false;


        for(int i = 0;i<Generator.NBR_INTERFACES;i++){
            //On récupère le schedule de l'interface
            List<Formation> schedule = sol.generateSchedule(i);
            //Coordonnées du centre de départ
            Center prevCenter = Generator.getCenterArray()[0];
            for(Formation f : schedule){
                //Pour chaque formation dans le schedule, on calcule l'heuristic à l'aide de la distance avec
                //la formation précédente
                Center currentCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                dist = Utils.calculateDist(prevCenter,currentCenter);
                spe = sol.isSpecialtyValid(f.getId());
                //calculate heuristic
                mvmtMatrix[f.getId()][i] = heuristic(dist, spe);
                prevCenter = currentCenter;
            }
            //dernier centre
            dist = Utils.calculateDist(prevCenter,Generator.getCenterArray()[0]);
            mvmtMatrix[Generator.NBR_FORMATIONS][i] = heuristic(dist, spe);
            //dist = 0;
        }

        return mvmtMatrix;
    }

    /**
     * Gets the minimal value of a given matrix (if the movement is not tabu)
     * Adds the optimal movement to the tabuList
     * @param matrix
     * @return the coordinates of the minimum value
     */
    public Pair<Integer, Integer> getMinimum(Double[][] matrix) {


        Pair<Integer, Integer> minimum = new Pair(0,0);

        for (int i = 0; i < Generator.NBR_INTERFACES; i++) {
            for (int j = 0; j < Generator.NBR_FORMATIONS; i++) {
                if (!tabuList.contains(new Pair(i,j))) {
                    if (matrix[i][j] < matrix[minimum.getX()][minimum.getY()]) {
                        minimum = new Pair(i,j);
                    }
                }
            }
        }
        //Add movement to tabuList
        addToTabuList(minimum);

        return minimum;
    }


    /**
     * Find a solution close to the one given in the parameters by trying to
     * optimize it
     * @param currentSol : current solution
     * @return neighborSol : better solution
     */
    public Solution computeNeighborhood(Solution currentSol){
        Solution neighborSol = currentSol;

        //Calculer la matrice
        //A l'aide de cette matrice, trouver des solutions voisines
        //Renvoyer une liste de solution voisines ?
        
        return neighborSol;
    }

    /**
     * Add a movement to the tabu list
     * @param i : TODO : ?
     */
    public void addToTabuList(Pair<Integer, Integer> i) {
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

        return closestNeighborSol;
    }

}
