import java.util.List;

public class Tabu {

    private Solution bestSolution;
    private double[][] matrix;
    private List<Integer> tabuList; //TODO: Modify
    private final int tabuLength;

    public Tabu(int tabuLength) {
        this.tabuLength = tabuLength;
    }

    public double heuristic(double dist, boolean spe) {
        //return spe(x) * dist(a,b)
        return 0;
    }


    public double[][] computeMatrix(Solution sol){
        double[][] mvmtMatrix = new double[Generator.NBR_FORMATIONS][Generator.NBR_INTERFACES];
        double dist = 0;
        boolean spe = false;

        for(int i = 0;i<Generator.NBR_INTERFACES;i++){
            List<Formation> schedule = sol.generateSchedule(i);
            Center prevCenter = Generator.getCenterArray()[0];
            for(Formation f : schedule){
                Center currentCenter = Generator.getCenterBySpeciality(f.getSpeciality().getId()+1);
                dist = Utils.calculateDist(prevCenter,currentCenter);
                spe = sol.isSpecialtyValid(f.getId());
                //calculate heuristic
                mvmtMatrix[f.getId()][i] = heuristic(dist, spe);
                prevCenter = currentCenter;
            }
            dist += Utils.calculateDist(prevCenter,Generator.getCenterArray()[0]);
            //calculate last heuristic
            mvmtMatrix[Generator.NBR_FORMATIONS][i] = heuristic(dist, spe);
            dist = 0;
        }

        //Calculer les schedules de chaque interfaces
        //Pour chaque interface, ajouter dans les formations correspondantes
        //la distance calculée entre la précédent et la prochaine

        //mettre -infini dans le reste de la colonne

        for(int i = 0; i<Generator.NBR_FORMATIONS;i++){
            mvmtMatrix[i][sol.getAssignation(i)] = 1; //TODO: heuristic
        }

        return mvmtMatrix;
    }
    
    public Solution computeNeighborhood(Solution currentSol){
        Solution neighborSol = currentSol;

        //Calculer la matrice
        //A l'aide de cette matrice, trouver des solutions voisines
        //Renvoyer une liste de solution voisines ?
        
        return neighborSol;
    }

    public void addToTabuList(int i) {
        tabuList.add(i);
        if (tabuList.size() >= tabuLength) {
            tabuList.remove(0);
        }
    }


}
