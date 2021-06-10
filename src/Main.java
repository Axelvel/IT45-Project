
//TODO : clean code

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

        Tabu testSearch = new Tabu(200);
        Solution testSol = Tabu.getClosestNeighborSol();

        //testSol.printAssignation();
        System.out.println("INITIAL SOLUTION : ");
        //testSol.showSolutionDetails();
        /*testSol.printAssignation();

        System.out.println("COPY : ");
        Solution testCopie = testSol;
        testCopie.printAssignation();

        System.out.println("TEST : ");
        testSol.setAssignation(0,15);
        testSol.printAssignation();
        testCopie.printAssignation();
*/      //testSol.showSolutionStats();
        testSol = testSearch.tabuSearch(testSol, 10);
        //testSol.showSolutionStats();

        //testSol.printAssignation();
        /*
        for(int i = 0; i < Generator.NBR_FORMATIONS;i++){
            System.out.println("F"+i+" : "+Generator.getFormationArray()[i].getSkill() + " - I"+testSol.getAssignation(i)+" : "+Generator.getInterfaceArray()[testSol.getAssignation(i)].getSkill());
        }

         */

        System.out.println("\nSolution valid : " + testSol.isValid());

        testSol.showSolutionStats();
        /*//
        Schedule test = new Schedule(Generator.getInterfaceArray()[2], testSol);
        System.out.println(test);
        System.out.println("\n");
        System.out.println((Generator.getFormationArray()[2]));
        //test.getSchedule().add((Generator.getFormationArray()[2]));
        System.out.println(test.fitInSchedule((Generator.getFormationArray()[2])));
        //test.addToSchedule(Generator.getFormationArray()[2]);
        //System.out.println(test);
        //System.out.println(test.contains((Generator.getFormationArray()[2])));*/

    }
}
