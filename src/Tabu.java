import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Implement the tabu class, used to resolve our optimization problem
 * TODO : more explanations
 */
public class Tabu {

    private Solution bestSolution;
    private double[][] matrix;
    private List<Utils.Pair<Integer, Integer>> tabuList = new ArrayList<>();

    private int tabuLength;

    public Tabu(int tabuLength) {
        if (tabuLength > 0) {
            this.tabuLength = tabuLength;
        } else {
            System.out.println("tabuLength needs to be > 0");
        }

    }


    public  Solution tabuSearch(Solution sol, long t) {

        //Nombre de changements de la bestSolution
        int z = 0;

        if (tabuList != null) {
            tabuList.clear();
        }

        bestSolution = new Solution(sol);

        System.out.println("\n\nInitial sol = " + sol.evalSolution());
        System.out.println("\nBestSol = " + bestSolution.evalSolution());

        Solution currentSol = new Solution(sol);


        for (int n = 0; n < 150; n++) {
            System.out.println("\nIteration "+ n);
            //System.out.println("Assignation matrix : ");
            double[][] matrix = computeMatrix(currentSol);
            //Utils.printMatrix(matrix);

            Utils.Pair<Integer, Integer> optimalMove =  getMinimum(matrix);
            currentSol.setAssignation(optimalMove.x, optimalMove.y); //TODO : check if this changes bestSolution as well

            System.out.println("Current solution eval : " + currentSol.evalSolution());
            System.out.println("Best solution eval : " + bestSolution.evalSolution());


            if (currentSol.evalSolution() < bestSolution.evalSolution()) {
                System.out.println("\n================== SWITCH ===================\n");
                z++;
                bestSolution = new Solution(currentSol);
            }
        }

        System.out.println("\n***** Done *****\n");

        System.out.println("Nombre de changements de bestSolution: = " + z + "\n");

        bestSolution.showSolutionDetails();
        //bestSolution.printAssignation();

        System.out.println("Initial sol = " + sol.evalSolution());
        System.out.println("BestSol = " + bestSolution.evalSolution());

        System.out.println(bestSolution.isValid());

        return bestSolution;
    }



    /**
     * Heuristic method, TODO : more explanations flemme
     * @param dist
     * @param spe
     * @return
     */
    public double heuristic(double dist, boolean spe) {

        double coefficient;

        if (spe) {
            coefficient = 1;
        } else {
            coefficient = 1.4;
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
        for (double[] row : mvmtMatrix) Arrays.fill(row, Double.NEGATIVE_INFINITY);
        double dist;
        boolean spe;

        //calculer le schedule
        //vérifier que itrface a au moins une formation le meme jour de formation
        //si oui, prend celle qui est juste avant formation et calculer l'heursitique
        //si non, calculer l'heuristique avec le centre 0
        for(Formation formation: Generator.getFormationArray()){
            for(int i = 0;i<Generator.NBR_INTERFACES;i++){
                //interface's schedule
                List<Formation> schedule = sol.generateSchedule(i);
                //center of the studied formation
                Center formationCenter = Generator.getCenterBySpeciality(formation.getSpeciality().getId());
                //By default, previous center is the beginning center
                Center prevCenter = Generator.getCenterArray()[0];

                if(schedule.contains(formation)){
                    mvmtMatrix[formation.getId()][i] = Double.NEGATIVE_INFINITY;
                }else{
                    //check if the interface already has a formation earlier that day. if yes, set
                    //the previous center to this formation center
                    for(Formation f: schedule) {
                        if(f.getDay() == formation.getDay() && f.getEndHour() < formation.getStartHour()){
                            prevCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                        }
                    }
                    //calculate the heuristic between the prevcenter and the possible formation center
                    dist = Utils.calculateDist(prevCenter,formationCenter);
                    spe = Generator.getInterfaceArray()[i].getSpecialities().contains(formationCenter.getSpeciality());
                    mvmtMatrix[formation.getId()][i] = heuristic(dist, spe);
                }
            }
        }
        return mvmtMatrix;
    }

     /** Find solutions close to the one given in the parameters by trying to
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

        //voir 2-opt ?
        //Solution initiale
        //DO
        //calculer solution voisine
        //Si sa valeur est mieux alors
        //la valeur initiale prend sa valeur
        //Jusqu’à « critère d’arrêt atteint »
        
        return neighborhood;
    }

    /**
     * Add a movement to the tabu list
     * @param i : TODO : ?
     */

    public void addToTabuList(Utils.Pair<Integer, Integer> i) {
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
    public static Solution getClosestNeighborSol(){
        //TODO : implement an easy but valid solution for test purposes
        Solution closestNeighborSol = new Solution();

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

    /**
     * Gets the minimal value of a given matrix (if the movement is not tabu)
     * Adds the optimal movement to the tabuList
     * @param matrix
     * @return the coordinates of the minimum value
     */
    public Utils.Pair<Integer, Integer> getMinimum(double[][] matrix) {

        double minValue = Double.POSITIVE_INFINITY;
        Utils.Pair<Integer, Integer> minimum = new Utils.Pair();


        for (int i = 0; i < Generator.NBR_FORMATIONS; i++) {
            for (int j = 0; j < Generator.NBR_INTERFACES; j++) {

                if (matrix[i][j] < minValue) {
                    if (!isTabu(new Utils.Pair<>(i, j))) {
                        minValue = matrix[i][j];
                        //
                        minimum.x = i;
                        minimum.y = j;
                    }


                }

            }
        }

        addToTabuList(minimum);

        return minimum;
    }

    public boolean isTabu(Utils.Pair<Integer, Integer> element) {
        for (int n = 0; n < tabuList.size(); n++) {
            if (tabuList.get(n).x == element.x && tabuList.get(n).y == element.y) {
                return true;
            }
        }

        return false;
    }


}
