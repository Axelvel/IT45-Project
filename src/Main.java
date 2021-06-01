

public class Main {

    public static void main(String[] args) {

        Generator instance = new Generator();
        System.out.println("\nFormations : " + instance.getFormationArray().length + "\n");
        for(int i = 0;i<instance.getFormationArray().length;i++){ instance.getFormationArray()[i].print(); }

        System.out.println("\nInterfaces : " + instance.getInterfaceArray().length + "\n");
        for(int i = 0;i<instance.getInterfaceArray().length;i++){ instance.getInterfaceArray()[i].print(); }

        System.out.println("\nCentres : " + instance.getCenterArray().length + "\n");
        for(int i = 0;i<instance.getCenterArray().length;i++){instance.getCenterArray()[i].print(); }

        System.out.println("\nCentres : " + instance.getCenterArray().length + "\n");
        for(int i = 0;i<instance.getCenterArray().length;i++){instance.getCenterArray()[i].print(); }

        double[][] M = Utils.computeDistMatrix();
        Utils.printMatrix(M);

        Solution solution = new Solution(25);
        solution.setAssignation(0, 2);
        //solution.setAssignation(1, 1);
       // solution.setAssignation(6, 2);
        solution.setAssignation(8, 2);
        solution.setAssignation(15, 2);
        solution.setAssignation(24, 2);
        //solution.setAssignation(6, 2);
        solution.printAssignation();
        //solution.printSchedule(2);
        boolean t = solution.isScheduleValid(2);
        System.out.println(t);

    }
}
