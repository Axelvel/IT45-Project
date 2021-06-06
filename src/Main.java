

public class Main {

    public static void main(String[] args) {

        Generator instance = new Generator();
        /*System.out.println("\nFormations : " + instance.getFormationArray().length + "\n");
        for(int i = 0;i<instance.getFormationArray().length;i++){ instance.getFormationArray()[i].print(); }

        System.out.println("\nInterfaces : " + instance.getInterfaceArray().length + "\n");
        for(int i = 0;i<instance.getInterfaceArray().length;i++){ instance.getInterfaceArray()[i].print(); }

        System.out.println("\nCentres : " + instance.getCenterArray().length + "\n");
        for(int i = 0;i<instance.getCenterArray().length;i++){instance.getCenterArray()[i].print(); }

        System.out.println("\nCentres : " + instance.getCenterArray().length + "\n");
        for(int i = 0;i<instance.getCenterArray().length;i++){instance.getCenterArray()[i].print(); }

        System.out.println("\nDISTANCES :");
        Utils.printMatrix(instance.getDistanceMatrix());*/

        Tabu testSearch = new Tabu(100);
        Solution testSol = testSearch.getClosestNeighborSol();
        //testSol.printAssignation();
        testSol.showSolutionDetails();

        System.out.println("Assignation matrix : ");
        double[][] matrix = testSearch.computeMatrix(testSol);
        Utils.printMatrix(matrix);

    }
}
