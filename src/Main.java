
//TODO : clean code

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException { //ADD A showDetails ARGUMENT
        //DEFAULTS VALUDES
        int nbApprenants = 80;
        boolean showDetails = false;

        //VALUES SET BY THE USER
        if(args.length > 0) nbApprenants = Integer.parseInt(args[0]);
        if(args.length > 1) showDetails = Boolean.parseBoolean(args[1]);

        System.out.println("############ TABU SEARCH EXAMPLE ############");
        System.out.println("Nombre d'apprenants : "+ nbApprenants);

        Generator instance = new Generator(nbApprenants);
        if(showDetails){
            System.out.println("INSTANCE :");
            instance.printInstance();
        }

        Tabu testSearch = new Tabu(200);
        Solution testSol = Tabu.getClosestNeighborSol();
        if(showDetails){
            System.out.println("Initial Solution :");
            testSol.showSolutionDetails();
        }

        testSol = testSearch.tabuSearch(testSol, 10, showDetails);

    }
}
