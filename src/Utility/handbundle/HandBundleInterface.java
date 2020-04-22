package Utility.handbundle;

import java.util.List;

public interface HandBundleInterface {

    /**
     * Get name of the hand to facilitate reflection in hand classificioant
     * @return
     */
    String getName();

    /**
     * Get the list of parameters that make up this hand
     * @return
     */
    List<Double> getParams();

    /**
     * Get the multiplier to help with payoffs
     * @return
     */
    double getMultiplier();

    /**
     * Get the name of the hand internally to the view
     * @return
     */
    String getViewName();

}
