package utils;

import models.*;

/**
 * Class containing multiple utils methods and classes
 */
public final class Utils {


    /**
     * Calculate the distance between two centers
     * @param ca : center a
     * @param cb : center b
     * @return double distance
     */
    public static double calculateDist(Center ca, Center cb) {
        int x1 = ca.getCoord().x;
        int y1 = ca.getCoord().y;
        int x2 = cb.getCoord().x;
        int y2 = cb.getCoord().y;

        return Math.sqrt(Math.pow(Math.abs(x1 - x2), 2)+ Math.pow(Math.abs(y1 - y2), 2));
    }

    /**
     * Check if an int is in an array
     * @param array : array to look into
     * @param v : value
     * @return true if v is in array, false if not
     */
    public static boolean contains(final int[] array, final int v) {
        boolean result = false;
        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }
        return result;
    }

}
