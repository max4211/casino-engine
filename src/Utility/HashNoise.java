package Utility;

import java.util.Calendar;

public interface HashNoise {

    static int addNoise(Object o) {
        Calendar calendar = Calendar.getInstance();
        double noise = (double) calendar.getTimeInMillis();
        double rand = Math.random();
        return o.hashCode() + (int) (noise * rand);
    }
}
