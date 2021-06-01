

public class Main {

    public static void main(String[] args) {

        Generator instance = new Generator();
        System.out.println("\nFormations : " + instance.getFormationList().size() + "\n");
        instance.getFormationList().forEach(value -> value.print());

        System.out.println("\nInterfaces : " + instance.getInterfaceList().size() + "\n");
        instance.getInterfaceList().forEach(value -> value.print());

        System.out.println("\nCentres : " + instance.getCenterList().size() + "\n");
        instance.getCenterList().forEach(value -> value.print());

        System.out.println("\nCentres : " + instance.getCenterList().size() + "\n");
        instance.getCenterList().forEach(value -> value.print());

        double[][] M = Utils.computeDistMatrix();
        Utils.printMatrix(M);

        Solution solution = new Solution(3);
        solution.setAssignation(0, 2);
        solution.setAssignation(1, 1);
        solution.setAssignation(2, 2);
        solution.printAssignation();
        solution.printSchedule(2);

    }
}
