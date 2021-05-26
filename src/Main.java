
public class Main {

    public static final Integer NBR_APPRENANTS = 80;
    public static final Integer NBR_COURS_PAR_SEMAINE = 1;
    public static final Integer NBR_CENTRES_FORMATION = 5;

    public static final float[][] coord = {
            {190,171}, /* centre 0 */

            /* Centres de formation */
            {37,10}, /* ecole formation SPECIALITE_MENUISERIE */
            {121,66}, /* ecole formation SPECIALITE_ELECTRICITE */
            {167,143}, /* ecole formation SPECIALITE_MECANIQUE */
            {20,86}, /* ecole formation SPECIALITE_INFORMATIQUE */
            {36,26}, /* ecole formation SPECIALITE_CUISINE */
    };

    public static void main(String[] args) {

        System.out.println("Hello world!");

        System.out.println(coord[1][0]);

        Solution s = new Solution();
        s.list.add(new Element(1, 2, 3));
        s.list.add(new Element(2, 4, 5));

        Utils.

        moy(s);



    }
}
