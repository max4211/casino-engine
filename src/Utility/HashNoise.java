package Utility;

import java.util.Calendar;

/**
 * Hash an object, add noise based on random number generator and date/time
 * @author Max Smith
 */
public interface HashNoise {

    /**
     * Add noise to the objects basic hash
     * @param o object to assign hashcode to, and to hash
     * @return integer valued noise to add to the hashcode
     */
    static int addNoise(Object o) {
        Calendar calendar = Calendar.getInstance();
        double noise = (double) calendar.getTimeInMillis();
        double rand = Math.random();
        return o.hashCode() + (int) (noise * rand);
    }
}
