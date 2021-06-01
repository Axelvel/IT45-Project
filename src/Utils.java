public final class Utils {


    public static double[][] computeDistMatrix() {
        double[][] M = new double[Generator.NBR_CENTRES_FORMATION][Generator.NBR_CENTRES_FORMATION];

        for (int i = 0; i < Generator.NBR_CENTRES_FORMATION; i++) {
            for (int j = 0; j < Generator.NBR_CENTRES_FORMATION; j++) {
                Center ca = Generator.getCenterList().get(i);
                Center cb = Generator.getCenterList().get(j);;

                int x1 = ca.getCoord().x;
                int y1 = ca.getCoord().y;
                int x2 = cb.getCoord().x;
                int y2 = cb.getCoord().y;

                if (ca != cb) {
                    M[i][j] = calculateDist(x1, y1, x2, y2);
                } else {
                    Center ci = Generator.getCenterList().get(0);
                    M[i][j] = calculateDist(ci.getCoord().x, ci.getCoord().y, x2, y2);
                }


            }
        }

        return M;
    }

    public static double calculateDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2)+ Math.pow(Math.abs(y1 - y2), 2));
    }


    public static void printMatrix(double [][] M) {
        for(int i = 0; i < M.length; i++) {
            for(int j = 0; j < M[i].length; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double dist(Center a, Center b) {
        return 0;
    }
}
