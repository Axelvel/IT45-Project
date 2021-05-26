import java.awt.geom.Point2D;

public class Utils {

    public double objective(Solution s) {

        int ecart = 1;
        double fcorr = 1.0;
        double z = 0.5 * (moy(s) + ecart) + 0.5 * fcorr * penalties(s);

        return z;
    }

    public static float moy(Solution s) {
        int value = 0;

        for (float[] element : Main.coord) {
            //value += Point2D.distance(x1, y1, x2, y2);
        }

        return value;
    }

    public float penalties(Solution s) {
        int v = 1;
        return v;
    }
}
