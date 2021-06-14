
import java.io.IOException;
import tabu.*;
import tabu.Solution;

public class Main {

    public static void main(String[] args) throws IOException {
        //DEFAULTS VALUES
        int nbApprenants = 80;
        boolean showDetails = false;
        long execTime = 10;
        int tabuLength = 500;

        //VALUES SET BY THE USER
        if(args.length > 0) showDetails = Boolean.parseBoolean(args[0]);
        if(args.length > 1) execTime = Long.parseLong(args[1]);
        if(args.length > 2) nbApprenants = Integer.parseInt(args[2]);
        if(args.length > 3) tabuLength = Integer.parseInt(args[3]);


        System.out.println("############ TABU SEARCH EXAMPLE ############");
        System.out.println("Nombre d'apprenants : "+ nbApprenants);
        System.out.println("Temps d'éxécution : "+ execTime +"s");
        System.out.println("Longueur de la liste taboue : "+ tabuLength +"s");

        Generator instance = new Generator(nbApprenants);
        if(showDetails){
            System.out.println("INSTANCE :");
            instance.printInstance();
        }

        Tabu search = new Tabu(tabuLength);
        Solution solution = Tabu.getClosestNeighborSol();
        if(showDetails){
            System.out.println("Initial Solution :");
            solution.showSolutionDetails();
        }

        try {
            solution = search.tabuSearch(solution, execTime, showDetails);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
