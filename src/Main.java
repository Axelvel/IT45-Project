
public class Main {

    public static void main(String[] args) {

        Generator instance = new Generator();
        System.out.println("\nFormations : " + instance.formationList.size() + "\n");
        instance.formationList.forEach(value -> value.print());

        System.out.println("\nInterfaces : " + instance.interfaceList.size() + "\n");
        instance.interfaceList.forEach(value -> value.print());

        System.out.println("\nCentres : " + instance.centerList.size() + "\n");
        instance.centerList.forEach(value -> value.print());
    }
}
