
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO : clean code
//TODO : timer

/**
 * Implement the tabu class, used to resolve our optimization problem
 * TODO : more explanations
 */
public class Tabu {

    private Solution bestSolution;
    private List<Utils.Pair<Integer, Integer>> tabuList = new ArrayList<>();
    private int tabuLength;

    public Tabu(int tabuLength) {
        if (tabuLength > 0) {
            this.tabuLength = tabuLength;
        } else {
            System.out.println("tabuLength needs to be > 0");
        }
    }

    /**
     * Starts a tabu search to optimize a solution
     * @param sol : initial solution
     * @param t : length of the tabu list //TODO: check
     * @param showDetails : if set to true, print details of the search
     * @return an optimized solution
     */
    public Solution tabuSearch(Solution sol, long t, boolean showDetails) throws IOException {

        //Nombre de changements de la bestSolution
        int nbSwitchs = 0;

        if (tabuList != null) tabuList.clear();

        bestSolution = new Solution(sol);
        Solution currentSol = new Solution(sol);

        if(showDetails) System.out.println("\nInitial solution = " + sol.evalSolution());

        for (int n = 0; n < 10000; n++) {
            if(showDetails) System.out.println("\nIteration n°"+ n);

            double[][] matrix = computeMatrix(currentSol);

            Utils.Pair<Integer, Integer> optimalMove =  getMinimum(matrix);

            if (optimalMove.x == null || optimalMove.y == null) {

                //Aspiration critera
                optimalMove.x = tabuList.get(0).x;
                optimalMove.y = tabuList.get(0).y;
                double min = matrix[optimalMove.x][optimalMove.y];

                for (int i =1; i < tabuList.size(); i++) {
                    if (matrix[tabuList.get(i).x][tabuList.get(i).y] < min) {
                        optimalMove.x = tabuList.get(i).x;
                        optimalMove.y = tabuList.get(i).y;
                    }
                }
            }
            currentSol.setAssignation(optimalMove.x, optimalMove.y);

            if(showDetails) System.out.println("Current solution evaluation : " + currentSol.evalSolution());
            if(showDetails) System.out.println("Best solution evaluation : " + bestSolution.evalSolution());


            if (currentSol.evalSolution() < bestSolution.evalSolution()) {
                if(showDetails) System.out.println("\n================== SWITCH ===================\n");
                nbSwitchs++;
                bestSolution = new Solution(currentSol);
            }
        }

        System.out.println("\n############ SEARCH DONE ############\n");

        if(showDetails) System.out.println("Number of best solution switches: = " + nbSwitchs + "\n");
        System.out.println("Initial solution evaluation = " + sol.evalSolution());
        System.out.println("Best solution evaluation = " + bestSolution.evalSolution());

        bestSolution.showSolutionDetails();
        //bestSolution.exportSolution();
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
            coefficient = 2; //TODO: Find right coefficient
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
        for (double[] row : mvmtMatrix) Arrays.fill(row, Double.POSITIVE_INFINITY);
        double dist;
        boolean spe;

        for(Interface i: Generator.getInterfaceArray()){
            Schedule schedule = new Schedule(i,sol);

            // Go through every possible formation that can be added to the schedule
            for(Formation futureFormation: Generator.getFormationArray()){
                //Get the center of the studied formation
                Center formationCenter = Generator.getCenterBySpeciality(futureFormation.getSpeciality().getId());
                //By default, previous center is the beginning center
                Center prevCenter = Generator.getCenterArray()[0];

                if(schedule.contains(futureFormation)){
                    mvmtMatrix[futureFormation.getId()][i.getId()] = Double.POSITIVE_INFINITY;
                }else{
                    if(schedule.fitInSchedule(futureFormation)){

                        for(Formation f: schedule.getSchedule()){
                            //if a formation is earlier that day, set previous center to the center of that formation
                            if(f.getDay() == futureFormation.getDay() && f.getStartHour() > futureFormation.getEndHour()){
                                prevCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                            }
                            dist = Utils.calculateDist(prevCenter,formationCenter);
                            spe = i.getSpecialities().contains(formationCenter.getSpeciality());

                            //if a formation is the last of the day, add the distance to the final center
                            if(schedule.isLastFormationOfTheDay(futureFormation)){
                                dist += Utils.calculateDist(formationCenter,Generator.getCenterArray()[Generator.NBR_CENTRES_FORMATION]);
                            }

                            mvmtMatrix[futureFormation.getId()][i.getId()] = heuristic(dist, spe);
                        }
                    }else{
                        mvmtMatrix[futureFormation.getId()][i.getId()] = Double.POSITIVE_INFINITY;
                    }
                }
            }
        }
        return mvmtMatrix;
    }


    /**
     * Add a movement to the tabu list
     * @param i : TODO : ?
     */

    public void addToTabuList(Utils.Pair<Integer, Integer> i) {
        tabuList.add(i);
        if (tabuList.size() >= tabuLength) tabuList.remove(0);
    }

    /**
     * Find the fastest and easiest valid solution, without caring about
     * optimization. Mostly used to find an initial solution.
     * @return a possible solution
     */
    public static Solution getClosestNeighborSol(){
        Solution closestNeighborSol = new Solution();

        int i = 0;

        for (int f = 0; f < Generator.getFormationArray().length; f++) {
           while (closestNeighborSol.getAssignation(f) == -1) {
               if (i >= Generator.NBR_INTERFACES) i = 0;

               closestNeighborSol.setAssignation(f,i);

               //check if this assignation is valid
               Interface inter = Generator.getInterfaceArray()[i];
               Schedule tempSchedule = new Schedule(inter, closestNeighborSol);
               if (!tempSchedule.isScheduleValid()) {
                   closestNeighborSol.setAssignation(f, -1);
                   i++;
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
                        minimum.x = i;
                        minimum.y = j;
                    }
                }
            }
        }

        addToTabuList(minimum);
        return minimum;
    }

    //TODO : add comments
    public boolean isTabu(Utils.Pair<Integer, Integer> element) {
        for (int n = 0; n < tabuList.size(); n++) {
            if (tabuList.get(n).x == element.x && tabuList.get(n).y == element.y) {
                return true;
            }
        }
        return false;
    }


}
